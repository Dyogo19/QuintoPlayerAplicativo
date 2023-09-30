package com.example.quintoplayerapp.jogos.jogo.data.domain

import com.example.quintoplayerapp.jogos.jogo.data.local.JogoModel
import com.example.quintoplayerapp.jogos.jogo.data.repository.JogoRepository

class JogoUseCase {

    private val repository by lazy { JogoRepository() }

    suspend fun getJogos(): List<JogoModel> {
        return repository.getJogos()
    }
}