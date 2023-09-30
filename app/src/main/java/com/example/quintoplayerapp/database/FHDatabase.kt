package com.example.quintoplayerapp.database


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.quintoplayerapp.App
import com.example.quintoplayerapp.database.converters.Converters
import com.example.quintoplayerapp.login.data.local.LoginDao
import com.example.quintoplayerapp.login.data.local.LoginEntity
import com.example.quintoplayerapp.login.data.local.UserDao
import com.example.quintoplayerapp.login.data.local.UserEntity

@Database(entities = [UserEntity::class , LoginEntity::class], version = 4)
@TypeConverters(Converters::class)
abstract class FHDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun loginDao() : LoginDao

    companion object {
        fun getInstance(): FHDatabase {
            return Room.databaseBuilder(
                App.context,
                FHDatabase::class.java,
                "fh.database"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
    }
}