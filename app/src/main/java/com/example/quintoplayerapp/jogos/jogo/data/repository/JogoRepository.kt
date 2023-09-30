package com.example.quintoplayerapp.jogos.jogo.data.repository

import android.util.Log
import com.example.quintoplayerapp.RetrofitClient
import com.example.quintoplayerapp.database.FHDatabase
import com.example.quintoplayerapp.jogos.jogo.data.JogoRequest
import com.example.quintoplayerapp.jogos.jogo.data.JogoResponse
import com.example.quintoplayerapp.jogos.jogo.data.local.JogoModel
import com.example.quintoplayerapp.login.data.local.LoginDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * Repository responsável por interagir com a camada de dados dos jogos.
 * Esta classe fornece métodos para criar um jogo e obter a lista de jogos.
 *
 * @property database Instância do banco de dados local.
 * @property client Instância do cliente Retrofit para comunicação com o servidor.
 */
class JogoRepository {

    private val database: FHDatabase by lazy {
        FHDatabase.getInstance()
    }

    private val client = RetrofitClient
        .createNetworkClient()
        .create(JogoService::class.java)

    /**
     * Cria um novo jogo no servidor.
     *
     * @param jogoRequest Objeto contendo informações do jogo a ser criado.
     * @return Verdadeiro se a operação foi bem-sucedida, falso caso contrário.
     */
    suspend fun createJogo(
        jogoRequest: JogoRequest
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.criarJogo(
                    database.loginDao().getId(),
                    JogoRequest(
                        jogoRequest.descricao,
                        jogoRequest.enderecoDoJogo,
                        jogoRequest.dataEHorario
                    )
                )
                response.isSuccessful
            } catch (exception: Exception) {
                false
            }
        }
    }

    /**
     * Obtém a lista de jogos a partir do servidor.
     *
     * @return Lista de objetos [JogoModel] representando os jogos.
     */
    suspend fun getJogos(): List<JogoModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<JogoResponse>> =
                client.buscarTodosOsJogos()

            if (response.isSuccessful) {
                val jogoResponseList: List<JogoResponse> = response.body() ?: emptyList()
                jogoResponseList.convertToModelList()
            } else {
                emptyList()
            }
        }
    }

    /**
     * Converte uma lista de [JogoResponse] em uma lista de [JogoModel].
     *
     * @return Lista de [JogoModel] convertida.
     */
    private fun List<JogoResponse>.convertToModelList(): List<JogoModel> {
        return map { it.jogoResponseToModel() }
    }

    /**
     * Converte um [JogoResponse] em um [JogoModel].
     *
     * @return [JogoModel] convertido.
     */
    private fun JogoResponse.jogoResponseToModel(): JogoModel {
        return JogoModel(
            descricao = descricao,
            enderecoDoJogo = enderecoDoJogo,
            dataEHorario = dataEHorario,
            nome = nome,
            numeroDeCelular = numeroDeCelular,
            idUsuario = idUsuario
        )
    }
}
