package com.example.quintoplayerapp.perfil.avaliacao.data.repository

import com.example.quintoplayerapp.perfil.avaliacao.data.AvaliacaoRequest
import com.example.quintoplayerapp.perfil.avaliacao.data.AvaliacaoResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Interface que define os endpoints da API para operações relacionadas a avaliações no perfil de usuário.
 */
interface AvaliacaoService {

    /**
     * Obtém a lista de avaliações associadas a um usuário com base em seu ID.
     *
     * @param idUsuario O ID do usuário para o qual as avaliações estão sendo obtidas.
     * @return Uma resposta [Response] contendo uma lista de [AvaliacaoResponse] ou um erro.
     */
    @GET("/api/v1/avaliacoes/{idUsuario}")
    suspend fun obterAvaliacoesPorUsuario(
        @Path("idUsuario") idUsuario: Int
    ): Response<List<AvaliacaoResponse>>

    /**
     * Cria uma nova avaliação para um usuário com base em seu ID.
     *
     * @param idUsuario O ID do usuário para o qual a avaliação está sendo criada.
     * @param avaliacaoRequest Um objeto [AvaliacaoRequest] contendo informações da avaliação.
     * @return Uma resposta [Response] indicando o sucesso ou falha da criação da avaliação.
     */
    @POST("/api/v1/avaliacoes/{idUsuario}")
    suspend fun criarAvaliacao(
        @Path("idUsuario") idUsuario: Int,
        @Body avaliacaoRequest: AvaliacaoRequest
    ): Response<ResponseBody>
}

