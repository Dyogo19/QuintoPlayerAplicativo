package com.example.quintoplayerapp.jogadores.jogador.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jogadorTable")
data class JogadorEntity(

    @PrimaryKey(autoGenerate = true)
    val idJogador: Int = 0,
    val nomeJogador: String,
    val dataEHoraLivrePraJogar: String,

    )
