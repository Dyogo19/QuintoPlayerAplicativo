package com.example.quintoplayerapp.jogos.jogo.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JogoResponse(
    val descricao: String,
    val enderecoDoJogo: String,
    val dataEHorario: String,
    val nome : String,
    val numeroDeCelular : String,
    val idUsuario : Int?


    )