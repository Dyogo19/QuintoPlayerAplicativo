package com.example.quintoplayerapp.perfil.avaliacao.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AvaliacaoResponse(
    val avaliacao: String,
    val nota: Int,
    val id : Int

)