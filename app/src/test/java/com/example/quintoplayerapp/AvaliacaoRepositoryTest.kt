package com.example.quintoplayerapp

import com.example.quintoplayerapp.database.FHDatabase
import com.example.quintoplayerapp.perfil.avaliacao.data.AvaliacaoRequest
import com.example.quintoplayerapp.perfil.avaliacao.data.AvaliacaoResponse
import com.example.quintoplayerapp.perfil.avaliacao.data.repository.AvaliacaoRepository
import com.example.quintoplayerapp.perfil.avaliacao.data.repository.AvaliacaoService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class AvaliacaoRepositoryTest {

    private lateinit var avaliacaoRepository: AvaliacaoRepository
    private lateinit var mockDatabase: FHDatabase
    private lateinit var mockAvaliacaoService: AvaliacaoService

    @Before
    fun setUp() {
        mockDatabase = mockk()
        mockAvaliacaoService = mockk()
        avaliacaoRepository = AvaliacaoRepository()
    }

    @Test
    fun testCreateAvaliacaoSuccess() = runBlocking {
        val avaliacaoRequest = AvaliacaoRequest("Avaliação", 5, 1)

        // Mock do retorno do serviço
        coEvery {
            mockAvaliacaoService.criarAvaliacao(any(), avaliacaoRequest)
        } returns Response.success(null)

        // Mock do ID do usuário no banco de dados
        coEvery {
            mockDatabase.loginDao().getId()
        } returns 1 // Substitua pelo valor desejado

        val result = avaliacaoRepository.createAvaliacao(avaliacaoRequest)
        assertEquals(true, result)
    }

    @Test
    fun testCreateAvaliacaoFailure() = runBlocking {
        val avaliacaoRequest = AvaliacaoRequest("Avaliação", 5, 1)

        // Mock do retorno do serviço com falha
        coEvery {
            mockAvaliacaoService.criarAvaliacao(any(), avaliacaoRequest)
        } returns Response.error(400, null)

        // Mock do ID do usuário no banco de dados
        coEvery {
            mockDatabase.loginDao().getId()
        } returns 1 // Substitua pelo valor desejado

        val result = avaliacaoRepository.createAvaliacao(avaliacaoRequest)
        assertEquals(false, result)
    }

    @Test
    fun testGetAvaliacoesSuccess() = runBlocking {
        // Mock do retorno do serviço
        val avaliacaoResponseList = listOf(AvaliacaoResponse("Avaliação", 5, 1))
        coEvery {
            mockAvaliacaoService.obterAvaliacoesPorUsuario(any())
        } returns Response.success(avaliacaoResponseList)

        val avaliacoes = avaliacaoRepository.getAvaliacoes()
        assertEquals(1, avaliacoes.size)
    }

    @Test
    fun testGetAvaliacoesDestinatarioSuccess() = runBlocking {
        // Mock do retorno do serviço para avaliações destinatário
        val avaliacaoResponseList = listOf(AvaliacaoResponse("Avaliação", 5, 1))
        coEvery {
            mockAvaliacaoService.obterAvaliacoesPorUsuario(any())
        } returns Response.success(avaliacaoResponseList)

        val avaliacoesDestinatario = avaliacaoRepository.getAvaliacoesDestinatario()
        assertEquals(1, avaliacoesDestinatario.size)
    }
}
