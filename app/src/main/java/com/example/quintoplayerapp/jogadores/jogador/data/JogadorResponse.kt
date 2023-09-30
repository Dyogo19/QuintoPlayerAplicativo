package com.example.quintoplayerapp.jogadores.jogador.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JogadorResponse(
    val nomeJogador: String,
    val dataEHoraLivrePraJogar: String,
    val nome : String,
    val numeroDeCelular : String,
    val idUsuario : Int?

)