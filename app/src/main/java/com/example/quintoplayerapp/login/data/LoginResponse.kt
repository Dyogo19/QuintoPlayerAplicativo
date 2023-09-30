package com.example.quintoplayerapp.login.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val id: Int,
    val email: String,
    val senha : String,
    val nome : String?,
    val numeroDeCelular : String?,

)