package com.example.quintoplayerapp

import com.example.quintoplayerapp.database.FHDatabase
import com.example.quintoplayerapp.jogadores.jogador.data.JogadorRequest
import com.example.quintoplayerapp.jogadores.jogador.data.JogadorResponse
import com.example.quintoplayerapp.jogadores.jogador.data.repository.JogadorRepository
import com.example.quintoplayerapp.jogadores.jogador.data.repository.JogadorService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class JogadorRepositoryTest {

    private lateinit var jogadorRepository: JogadorRepository
    private lateinit var mockDatabase: FHDatabase
    private lateinit var mockJogadorService: JogadorService

    @Before
    fun setUp() {
        mockDatabase = mockk()
        mockJogadorService = mockk()
        jogadorRepository = JogadorRepository()
    }

    @Test
    fun testCreateJogadorSuccess() = runBlocking {
        val jogadorRequest = JogadorRequest("Nome do Jogador", "2023-09-28'T'19:00")

        // Mock do retorno do serviço
        coEvery {
            mockJogadorService.criarJogador(any(), jogadorRequest)
        } returns Response.success(ResponseBody.create(null, ""))

        // Mock do ID do usuário no banco de dados
        coEvery {
            mockDatabase.loginDao().getId()
        } returns 1 // Substitua pelo valor desejado

        val result = jogadorRepository.createJogador(jogadorRequest)
        assertEquals(true, result)
    }

    @Test
    fun testCreateJogadorFailure() = runBlocking {
        val jogadorRequest = JogadorRequest("Nome do Jogador", "2023-09-28'T'19:00")

        // Mock do retorno do serviço com falha
        coEvery {
            mockJogadorService.criarJogador(any(), jogadorRequest)
        } returns Response.error(400, ResponseBody.create(null, ""))

        // Mock do ID do usuário no banco de dados
        coEvery {
            mockDatabase.loginDao().getId()
        } returns 1 // Substitua pelo valor desejado

        val result = jogadorRepository.createJogador(jogadorRequest)
        assertEquals(false, result)
    }

    @Test
    fun testGetJogadoresSuccess() = runBlocking {
        // Mock do retorno do serviço
        coEvery {
            mockJogadorService.buscarTodosOsJogadores()
        } returns Response.success(listOf(JogadorResponse("Nome", "2023-09-28'T'19:00", "Nome", "123", 1)))

        val jogadores = jogadorRepository.getJogadores()
        assertEquals(1, jogadores.size)
    }

    @Test
    fun testGetJogadoresFailure() = runBlocking {
        // Mock do retorno do serviço com falha
        coEvery {
            mockJogadorService.buscarTodosOsJogadores()
        } returns Response.error(400, ResponseBody.create(null, ""))

        val jogadores = jogadorRepository.getJogadores()
        assertEquals(0, jogadores.size)
    }
}
