package com.example.quintoplayerapp.jogadores.jogador.data.domain

import com.example.quintoplayerapp.jogadores.jogador.data.local.JogadorModel
import com.example.quintoplayerapp.jogadores.jogador.data.repository.JogadorRepository

class JogadorUseCase {

    private val repository by lazy { JogadorRepository() }

    suspend fun getJogadores(): List<JogadorModel> {
        return repository.getJogadores()
    }
}