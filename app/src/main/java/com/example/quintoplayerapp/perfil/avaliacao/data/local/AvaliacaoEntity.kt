package com.example.quintoplayerapp.perfil.avaliacao.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "avaliacaoTable")
data class AvaliacaoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val avaliacao: String,
    val nota: Int,
)