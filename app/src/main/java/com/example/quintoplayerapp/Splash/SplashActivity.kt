package com.example.quintoplayerapp.Splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.quintoplayerapp.R
import com.example.quintoplayerapp.home.HomeActivity
import com.example.quintoplayerapp.login.view.LoginActivity


class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                SplashViewState.ShowHome -> showHome()
                SplashViewState.ShowLogin -> showLogin()
            }
        }
    }

    private fun showHome() {
        startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
        finish()
    }
    private fun showLogin() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

}