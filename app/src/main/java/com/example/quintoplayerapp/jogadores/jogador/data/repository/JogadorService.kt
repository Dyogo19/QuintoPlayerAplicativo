package com.example.quintoplayerapp.jogadores.jogador.data.repository

import com.example.quintoplayerapp.jogadores.Jogador
import com.example.quintoplayerapp.jogadores.jogador.data.JogadorRequest
import com.example.quintoplayerapp.jogadores.jogador.data.JogadorResponse
import com.example.quintoplayerapp.jogos.jogo.data.JogoRequest
import com.example.quintoplayerapp.jogos.jogo.data.JogoResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Interface que define os serviços relacionados aos jogadores no servidor.
 */
interface JogadorService {

    /**
     * Obtém a lista de todos os jogadores cadastrados no servidor.
     *
     * @return Um objeto [Response] contendo uma lista de [JogadorResponse].
     */
    @GET("/api/v1/jogadores")
    suspend fun buscarTodosOsJogadores(): Response<List<JogadorResponse>>

    /**
     * Cria um novo jogador no servidor associado ao usuário com o ID especificado.
     *
     * @param idUsuario O ID do usuário que está criando o jogador.
     * @param jogadorRequest Objeto contendo informações do jogador a ser criado.
     * @return Um objeto [Response] indicando o resultado da operação.
     */
    @POST("/api/v1/jogadores/{idUsuario}")
    suspend fun criarJogador(
        @Path("idUsuario") idUsuario:Int,
        @Body jogadorRequest: JogadorRequest): Response<ResponseBody>

}