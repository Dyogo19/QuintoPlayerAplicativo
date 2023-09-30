package com.example.quintoplayerapp.login.domain

import com.example.quintoplayerapp.login.data.LoginRequest
import com.example.quintoplayerapp.login.data.repository.LoginRepository

class LoginUseCase {
    private val repository by lazy { LoginRepository() }


    suspend fun login(loginRequest: LoginRequest): Boolean {
        return repository.login(loginRequest)
    }

}