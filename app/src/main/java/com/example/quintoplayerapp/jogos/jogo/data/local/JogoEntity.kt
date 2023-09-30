package com.example.quintoplayerapp.jogos.jogo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "jogoTable")
data class JogoEntity(

    @PrimaryKey(autoGenerate = true)
    val idJogo: Int = 0,
    val descricao: String,
    val enderecoDoJogo: String,
    val dataEHorario: String,

    )
