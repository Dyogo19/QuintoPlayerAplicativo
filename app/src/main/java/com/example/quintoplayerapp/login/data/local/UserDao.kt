package com.example.quintoplayerapp.login.data.local

import androidx.room.Dao
import androidx.room.Insert
import retrofit2.http.Query
import java.util.*

@Dao
interface UserDao {
    @Insert
    fun insertUser(userEntity: UserEntity)

    @androidx.room.Query("SELECT * from  userTable")
    fun getUser(): List<UserEntity>

    @androidx.room.Query("DELETE from userTable")
    fun clearCache()

    @androidx.room.Query("SELECT id from userTable")
    fun getId(): Int
}