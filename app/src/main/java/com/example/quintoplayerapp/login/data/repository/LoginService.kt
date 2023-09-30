package com.example.quintoplayerapp.login.data.repository

import com.example.quintoplayerapp.login.data.LoginRequest
import com.example.quintoplayerapp.login.data.LoginResponse
import com.example.quintoplayerapp.login.data.UserRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Interface que define os métodos para realizar chamadas de API relacionadas à autenticação e registro de usuários.
 */
interface LoginService {

    /**
     * Realiza uma solicitação POST para autenticar um usuário com base nas informações fornecidas.
     *
     * @param loginRequest Um objeto [LoginRequest] contendo informações de login (email e senha).
     * @return Uma [Response] contendo um objeto [LoginResponse] com informações de autenticação ou erro.
     */
    @POST("/api/v1/login")
    suspend fun getUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    /**
     * Realiza uma solicitação POST para registrar um novo usuário com base nas informações fornecidas.
     *
     * @param userRequest Um objeto [UserRequest] contendo informações do novo usuário (nome, email, senha e número de celular).
     * @return Uma [Response] contendo um [ResponseBody] indicando o sucesso ou erro do registro.
     */
    @POST("/api/v1/usuarios")
    suspend fun postUser(@Body userRequest: UserRequest): Response<ResponseBody>
}