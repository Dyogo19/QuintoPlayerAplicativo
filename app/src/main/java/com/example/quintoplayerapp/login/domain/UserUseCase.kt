package com.example.quintoplayerapp.login.domain

import com.example.quintoplayerapp.login.data.repository.LoginRepository

class UserUseCase {
    private val repository by lazy { LoginRepository() }

    suspend fun createUser(nome:String, email: String, senha: String, numeroDeCelular: String): Boolean {
        return repository.createUser(nome=nome,email = email, senha = senha, numeroDeCelular = numeroDeCelular)
    }
}