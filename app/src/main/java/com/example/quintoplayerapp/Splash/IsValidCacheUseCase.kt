package com.example.quintoplayerapp.Splash

import com.example.quintoplayerapp.login.data.repository.LoginRepository
import java.util.*
import java.util.concurrent.TimeUnit

private const val MINUTES_TO_CACHE = 1

class IsValidCacheUseCase {

    private val repository by lazy { LoginRepository() }

    suspend fun isValidCache(): Boolean {
        val cacheDate = repository.getCacheDate() ?: return false
        val minutes = Date().minutesAfter(cacheDate)
        val isValidCache = minutes <= MINUTES_TO_CACHE
        if (!isValidCache) repository.clearCache()

        return isValidCache
    }

    private fun Date.minutesAfter(date: Date): Long =
        ((this.time - date.time) / TimeUnit.MINUTES.toMillis(1))

}