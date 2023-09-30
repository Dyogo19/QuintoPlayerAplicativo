package com.example.quintoplayerapp.perfil.avaliacao.data.domain

import com.example.quintoplayerapp.perfil.avaliacao.data.local.AvaliacaoModel
import com.example.quintoplayerapp.perfil.avaliacao.data.repository.AvaliacaoRepository

class AvaliacaoUseCase {

    private val repository by lazy { AvaliacaoRepository() }

    suspend fun getAvaliacoes(): List<AvaliacaoModel> {
        return repository.getAvaliacoes()
    }
}
