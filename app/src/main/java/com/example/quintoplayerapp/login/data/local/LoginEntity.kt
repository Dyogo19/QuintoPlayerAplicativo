package com.example.quintoplayerapp.login.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "loginTable")
data class LoginEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val senha : String?,
    val nome : String?,
    val numeroDeCelular : String?,
    val lastLoginTime: Date = Date()
)