package com.example.quintoplayerapp.jogadores.jogador.data.repository

import com.example.quintoplayerapp.RetrofitClient
import com.example.quintoplayerapp.database.FHDatabase
import com.example.quintoplayerapp.jogadores.jogador.data.JogadorRequest
import com.example.quintoplayerapp.jogadores.jogador.data.JogadorResponse
import com.example.quintoplayerapp.jogadores.jogador.data.local.JogadorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Repository responsável por interagir com a camada de dados dos jogadores.
 * Esta classe fornece métodos para criar um jogador e obter a lista de jogadores.
 *
 * @property database Instância do banco de dados local.
 * @property client Instância do cliente Retrofit para comunicação com o servidor.
 */
class JogadorRepository {

    private val database: FHDatabase by lazy {
        FHDatabase.getInstance()
    }

    private val client = RetrofitClient
        .createNetworkClient()
        .create(JogadorService::class.java)


    /**
     * Cria um novo jogador no servidor.
     *
     * @param jogadorRequest Objeto contendo informações do jogador a ser criado.
     * @return Verdadeiro se a operação foi bem-sucedida, falso caso contrário.
     */

    suspend fun createJogador(
        jogadorRequest: JogadorRequest
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = client.criarJogador(
                    database.loginDao().getId(),
                    JogadorRequest(
                        jogadorRequest.nomeJogador,
                        jogadorRequest.dataEHoraLivrePraJogar

                    )
                )
                response.isSuccessful
            } catch (exception: Exception) {
                false
            }
        }
    }

    /**
     * Obtém a lista de jogadores a partir do servidor.
     *
     * @return Lista de objetos [JogadorModel] representando os jogadores.
     */
    suspend fun getJogadores(): List<JogadorModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<JogadorResponse>> =
                client.buscarTodosOsJogadores()

            if (response.isSuccessful) {
                val jogadorResponseList: List<JogadorResponse> = response.body() ?: emptyList()
                jogadorResponseList.convertToModelList()
            } else {
                emptyList()
            }
        }
    }

    /**
     * Converte uma lista de [JogadorResponse] em uma lista de [JogadorModel].
     *
     * @return Lista de [JogadorModel] convertida.
     */

    private fun List<JogadorResponse>.convertToModelList(): List<JogadorModel> {
        return map { it.jogadorResponseToModel() }
    }

    /**
     * Converte um [JogadorResponse] em um [JogadorModel].
     *
     * @return [JogadorModel] convertido.
     */

    private fun JogadorResponse.jogadorResponseToModel(): JogadorModel {
        return JogadorModel(
            nomeJogador = nomeJogador,
            dataEHoraLivrePraJogar = dataEHoraLivrePraJogar,
            nome = nome,
            numeroDeCelular = numeroDeCelular,
            idUsuario = idUsuario
        )
    }

}
