package com.example.quintoplayerapp

import com.example.quintoplayerapp.database.FHDatabase
import com.example.quintoplayerapp.login.data.LoginRequest
import com.example.quintoplayerapp.login.data.LoginResponse
import com.example.quintoplayerapp.login.data.UserRequest
import com.example.quintoplayerapp.login.data.repository.LoginRepository
import com.example.quintoplayerapp.login.data.repository.LoginService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.util.*

class LoginRepositoryTest {

    private lateinit var loginRepository: LoginRepository
    private lateinit var mockDatabase: FHDatabase
    private lateinit var mockLoginService: LoginService

    @Before
    fun setUp() {
        mockDatabase = mockk()
        mockLoginService = mockk()
        loginRepository = LoginRepository()
    }

    @Test
    fun testLoginSuccess() = runBlocking {
        val loginRequest = LoginRequest("email@example.com", "senha")

        // Mock do retorno do serviço
        coEvery {
            mockLoginService.getUser(loginRequest)
        } returns Response.success(LoginResponse(1, "email@example.com", "senha", "Nome", "123456"))

        // Mock do salvamento do usuário no banco de dados
        coEvery {
            mockDatabase.loginDao().insertLogin(any())
        } returns Unit

        val result = loginRepository.login(loginRequest)
        assertEquals(true, result)
    }

    @Test
    fun testLoginFailure() = runBlocking {
        val loginRequest = LoginRequest("email@example.com", "senha")

        // Mock do retorno do serviço com falha
        coEvery {
            mockLoginService.getUser(loginRequest)
        } returns Response.error(400, ResponseBody.create(null, ""))

        val result = loginRepository.login(loginRequest)
        assertEquals(false, result)
    }

    @Test
    fun testCreateUserSuccess() = runBlocking {
        val nome = "Nome"
        val email = "email@example.com"
        val senha = "senha"
        val numeroDeCelular = "123456"

        // Mock do retorno do serviço
        coEvery {
            mockLoginService.postUser(UserRequest(nome, email, senha, numeroDeCelular))
        } returns Response.success(ResponseBody.create(null, ""))

        val result = loginRepository.createUser(nome, email, senha, numeroDeCelular)
        assertEquals(true, result)
    }

    @Test
    fun testCreateUserFailure() = runBlocking {
        val nome = "Nome"
        val email = "email@example.com"
        val senha = "senha"
        val numeroDeCelular = "123456"

        // Mock do retorno do serviço com falha
        coEvery {
            mockLoginService.postUser(UserRequest(nome, email, senha, numeroDeCelular))
        } returns Response.error(400, ResponseBody.create(null, ""))

        val result = loginRepository.createUser(nome, email, senha, numeroDeCelular)
        assertEquals(false, result)
    }

    @Test
    fun testGetCacheDate() = runBlocking {
        val cacheDate = Date()

        // Mock do retorno da data de cache do banco de dados
        coEvery {
            mockDatabase.loginDao().getLoginDate()
        } returns cacheDate

        val result = loginRepository.getCacheDate()
        assertEquals(cacheDate, result)
    }

    @Test
    fun testClearCache() = runBlocking {
        // Mock da chamada para limpar o cache no banco de dados
        coEvery {
            mockDatabase.loginDao().clearCache()
        } returns Unit

        loginRepository.clearCache()
    }
}