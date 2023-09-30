package com.example.quintoplayerapp.login.data.repository

import android.util.Log
import com.example.quintoplayerapp.RetrofitClient
import com.example.quintoplayerapp.database.FHDatabase
import com.example.quintoplayerapp.login.data.LoginRequest
import com.example.quintoplayerapp.login.data.LoginResponse
import com.example.quintoplayerapp.login.data.UserRequest
import com.example.quintoplayerapp.login.data.UserResponse
import com.example.quintoplayerapp.login.data.local.LoginEntity
import com.example.quintoplayerapp.login.data.local.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.*

/**
 * Repository responsável por interagir com a camada de dados de autenticação e usuários.
 * Esta classe fornece métodos para login, registro de usuário, armazenamento em cache e outras operações relacionadas à autenticação.
 *
 * @property database Instância do banco de dados local.
 * @property client Instância do cliente Retrofit para comunicação com o servidor.
 */
class LoginRepository {
    private val database: FHDatabase by lazy {
        FHDatabase.getInstance()
    }

    private val client =
        RetrofitClient
            .createNetworkClient()
            .create(LoginService::class.java)


    /**
     * Realiza o processo de login no servidor com base nas informações fornecidas.
     *
     * @param loginRequest Objeto contendo informações de login (email e senha).
     * @return Verdadeiro se o login foi bem-sucedido, falso caso contrário.
     */
    suspend fun login(loginRequest: LoginRequest): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.getUser(
                    loginRequest)
                saveUserLogin(response)
                response.isSuccessful
            } catch (exception: Exception) {
                false
            }
        }
    }

    /**
     * Salva as informações do usuário logado no banco de dados local.
     *
     * @param login Resposta de login bem-sucedido contendo informações do usuário.
     */


    private suspend fun saveUserLogin(login: Response<LoginResponse>) {
        return withContext(Dispatchers.IO) {
            if (login.isSuccessful) {
                login.body()?.run {
                    database.loginDao().insertLogin(
                        userResponseToEntity()
                    )
                }
            }
        }
    }

    /**
     * Converte a resposta do servidor em uma entidade de login local.
     *
     * @return Uma [LoginEntity] contendo informações do usuário logado.
     */

    private fun LoginResponse.userResponseToEntity(): LoginEntity {
        return LoginEntity(
            id = id,
            email = email,
            senha = senha,
            nome = nome,
            numeroDeCelular = numeroDeCelular
        )
    }

    private fun UserResponse.userResponseToEntity(): UserEntity{
        return UserEntity(
            id = idUser ,
            nome = nome,
            email = email,
            senha = senha,
            numeroDeCelular = numeroDeCelular

        )
    }

    /**
     * Realiza o registro de um novo usuário no servidor com as informações fornecidas.
     *
     * @param nome Nome do usuário.
     * @param email Endereço de e-mail do usuário.
     * @param senha Senha do usuário.
     * @param numeroDeCelular Número de celular do usuário.
     * @return Verdadeiro se o registro foi bem-sucedido, falso caso contrário.
     */

    suspend fun createUser(nome: String, email: String, senha: String, numeroDeCelular: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.postUser(UserRequest(nome, email, senha, numeroDeCelular))
                response.isSuccessful
            } catch (exception: Exception) {
                false
            }
        }

    }


    /**
     * Obtém a data de armazenamento em cache, se disponível.
     *
     * @return A data de armazenamento em cache como um objeto [Date] ou nulo se não estiver disponível.
     */
    suspend fun getCacheDate(): Date? {
        return withContext(Dispatchers.IO) {
            database.loginDao().getLoginDate()
        }
    }

    /**
     * Limpa os dados armazenados em cache.
     */

    suspend fun clearCache() {
        return withContext(Dispatchers.IO) {
            database.loginDao().clearCache()
        }
    }

}