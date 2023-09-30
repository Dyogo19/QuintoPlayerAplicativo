package com.example.quintoplayerapp.jogos.jogo.data.repository


import com.example.quintoplayerapp.jogos.jogo.data.JogoRequest
import com.example.quintoplayerapp.jogos.jogo.data.JogoResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Interface que define os serviços relacionados aos jogos no servidor.
 */
interface JogoService {

    /**
     * Obtém a lista de todos os jogos cadastrados no servidor.
     *
     * @return Um objeto [Response] contendo uma lista de [JogoResponse].
     */
    @GET("/api/v1/jogos")
    suspend fun buscarTodosOsJogos(): Response<List<JogoResponse>>

    /**
     * Cria um novo jogo no servidor associado ao usuário com o ID especificado.
     *
     * @param idUsuario O ID do usuário que está criando o jogo.
     * @param jogoRequest Objeto contendo informações do jogo a ser criado.
     * @return Um objeto [Response] indicando o resultado da operação.
     */
    @POST("/api/v1/jogos/{idUsuario}")
    suspend fun criarJogo(
        @Path("idUsuario") idUsuario: Int,
        @Body jogoRequest: JogoRequest
    ): Response<ResponseBody>
}

