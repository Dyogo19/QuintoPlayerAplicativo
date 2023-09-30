package com.example.quintoplayerapp

import com.example.quintoplayerapp.database.FHDatabase
import com.example.quintoplayerapp.jogos.jogo.data.JogoRequest
import com.example.quintoplayerapp.jogos.jogo.data.JogoResponse
import com.example.quintoplayerapp.jogos.jogo.data.repository.JogoRepository
import com.example.quintoplayerapp.jogos.jogo.data.repository.JogoService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class JogoRepositoryTest {

    private lateinit var jogoRepository: JogoRepository
    private lateinit var mockDatabase: FHDatabase
    private lateinit var mockJogoService: JogoService

    @Before
    fun setUp() {
        mockDatabase = mockk()
        mockJogoService = mockk()
        jogoRepository = JogoRepository()
    }

    @Test
    fun testCreateJogoSuccess() = runBlocking {
        val jogoRequest = JogoRequest("Descrição do Jogo", "Endereço do Jogo", "2023-09-28'T'19:00")

        // Mock do retorno do serviço
        coEvery {
            mockJogoService.criarJogo(any(), jogoRequest)
        } returns Response.success(ResponseBody.create(null, ""))

        // Mock do ID do usuário no banco de dados
        coEvery {
            mockDatabase.loginDao().getId()
        } returns 1 // Substitua pelo valor desejado

        val result = jogoRepository.createJogo(jogoRequest)
        assertEquals(true, result)
    }

    @Test
    fun testCreateJogoFailure() = runBlocking {
        val jogoRequest = JogoRequest("Descrição do Jogo", "Endereço do Jogo", "2023-09-28'T'19:00")

        // Mock do retorno do serviço com falha
        coEvery {
            mockJogoService.criarJogo(any(), jogoRequest)
        } returns Response.error(400, ResponseBody.create(null, ""))

        // Mock do ID do usuário no banco de dados
        coEvery {
            mockDatabase.loginDao().getId()
        } returns 1 // Substitua pelo valor desejado

        val result = jogoRepository.createJogo(jogoRequest)
        assertEquals(false, result)
    }

    @Test
    fun testGetJogosSuccess() = runBlocking {
        // Mock do retorno do serviço
        val jogoResponseList = listOf(JogoResponse("Descrição", "Endereço", "2023-09-28'T'19:00", "Nome", "123", 1))
        coEvery {
            mockJogoService.buscarTodosOsJogos()
        } returns Response.success(jogoResponseList)

        val jogos = jogoRepository.getJogos()
        assertEquals(1, jogos.size)
    }

    @Test
    fun testGetJogosFailure() = runBlocking {
        // Mock do retorno do serviço com falha
        coEvery {
            mockJogoService.buscarTodosOsJogos()
        } returns Response.error(400, ResponseBody.create(null, ""))

        val jogos = jogoRepository.getJogos()
        assertEquals(0, jogos.size)
    }
}
