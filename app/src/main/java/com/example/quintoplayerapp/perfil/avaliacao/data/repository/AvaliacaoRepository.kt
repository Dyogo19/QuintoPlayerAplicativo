package com.example.quintoplayerapp.perfil.avaliacao.data.repository

import android.util.Log
import com.example.quintoplayerapp.RetrofitClient
import com.example.quintoplayerapp.database.FHDatabase
import com.example.quintoplayerapp.perfil.avaliacao.data.AvaliacaoRequest
import com.example.quintoplayerapp.perfil.avaliacao.data.AvaliacaoResponse
import com.example.quintoplayerapp.perfil.avaliacao.data.local.AvaliacaoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Classe responsável por interagir com a camada de dados de avaliações no perfil de usuário.
 * Fornece métodos para criar avaliações, obter avaliações e outras operações relacionadas às avaliações.
 *
 * @property database Instância do banco de dados local.
 * @property client Instância do cliente Retrofit para comunicação com o servidor.
 */
class AvaliacaoRepository {

    private val database: FHDatabase by lazy {
        FHDatabase.getInstance()
    }

    private val client = RetrofitClient
        .createNetworkClient()
        .create(AvaliacaoService::class.java)

    /**
     * Cria uma avaliação para um usuário com base nas informações fornecidas.
     *
     * @param avaliacaoRequest Um objeto [AvaliacaoRequest] contendo informações da avaliação.
     * @return Verdadeiro se a criação da avaliação foi bem-sucedida, falso caso contrário.
     */
    suspend fun createAvaliacao(avaliacaoRequest: AvaliacaoRequest): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.criarAvaliacao(
                    database.loginDao().getId(),
                    AvaliacaoRequest(
                        avaliacaoRequest.avaliacao,
                        avaliacaoRequest.nota,
                        avaliacaoRequest.idUsuarioDestinatario
                    )
                )
                response.isSuccessful
            } catch (exception: Exception) {
                false
            }
        }
    }

    /**
     * Obtém a lista de avaliações associadas ao usuário atualmente autenticado.
     *
     * @return Uma lista de objetos [AvaliacaoModel] representando as avaliações do usuário ou uma lista vazia se não houver avaliações.
     */
    suspend fun getAvaliacoes(): List<AvaliacaoModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<AvaliacaoResponse>> =
                client.obterAvaliacoesPorUsuario(database.loginDao().getId())
            if (response.isSuccessful) {
                val avaliacaoResponseList: List<AvaliacaoResponse> = response.body() ?: emptyList()
                avaliacaoResponseList.convertToModelList()
            } else {
                emptyList()
            }
        }
    }

    /**
     * Obtém a lista de avaliações destinadas ao usuário atualmente autenticado.
     *
     * @return Uma lista de objetos [AvaliacaoModel] representando as avaliações destinadas ao usuário ou uma lista vazia se não houver avaliações.
     */
    suspend fun getAvaliacoesDestinatario(): List<AvaliacaoModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<AvaliacaoResponse>> =
                client.obterAvaliacoesPorUsuario(database.loginDao().getId())
            if (response.isSuccessful) {
                val avaliacaoResponseList: List<AvaliacaoResponse> = response.body() ?: emptyList()
                avaliacaoResponseList.convertToModelList()
            } else {
                emptyList()
            }
        }
    }

    /**
     * Converte uma lista de [AvaliacaoResponse] em uma lista de [AvaliacaoModel].
     *
     * @return Uma lista de objetos [AvaliacaoModel].
     */
    private fun List<AvaliacaoResponse>.convertToModelList(): List<AvaliacaoModel> {
        return map { it.avaliacaoResponseToModel() }
    }

    /**
     * Converte uma [AvaliacaoResponse] em um [AvaliacaoModel].
     *
     * @return Um objeto [AvaliacaoModel].
     */
    private fun AvaliacaoResponse.avaliacaoResponseToModel(): AvaliacaoModel {
        return AvaliacaoModel(
            avaliacao = avaliacao,
            nota = nota,
            idUsuarioDestinatario = id
        )
    }
}
