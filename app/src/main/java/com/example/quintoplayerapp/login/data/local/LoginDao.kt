package com.example.quintoplayerapp.login.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface LoginDao {
    @Insert
    fun insertLogin(loginEntity: LoginEntity)

    @Query("SELECT * from  loginTable")
    fun getLogin(): List<LoginEntity>

    @Query("DELETE from loginTable")
    fun clearCache()

    @Query("SELECT lastLoginTime from loginTable")
    fun getLoginDate(): Date?

    @Query("SELECT id from loginTable")
    fun getId(): Int

    @Query("SELECT nome from loginTable")
    fun getNome() : String

    @Query("SELECT numeroDeCelular from loginTable")
    fun getNumero() : String

}