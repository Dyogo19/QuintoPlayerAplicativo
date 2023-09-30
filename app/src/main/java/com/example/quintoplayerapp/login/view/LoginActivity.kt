package com.example.quintoplayerapp.login.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.module.components.hide
import com.example.module.components.show
import com.example.quintoplayerapp.R
import com.example.quintoplayerapp.database.FHDatabase
import com.example.quintoplayerapp.databinding.ActivityLoginBinding
import com.example.quintoplayerapp.home.HomeActivity
import com.example.quintoplayerapp.login.presentation.LoginViewModel
import com.example.quintoplayerapp.login.presentation.model.LoginViewState
import com.example.quintoplayerapp.profile.ProfileActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initializeObserver()

        binding.btLogin.setOnClickListener {
            viewModel.validateInputs(
                senha = binding.pwd.text.toString(),
                email = binding.email.text.toString(),
                )
        }

        binding.novo.setOnClickListener{
            showNovo()
        }
    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                LoginViewState.ShowHomeScreen -> showHome()
                LoginViewState.ShowErrorMessage -> showSnackError()
                LoginViewState.ShowEmailErrorMessage -> showEmailError()
                LoginViewState.ShowPasswordErrorMessage -> showPasswordError()
                LoginViewState.ShowLoading -> showLoading()
            }
        }
    }


    private fun showLoading() {

        binding.pbLoading.show()
    }

    private fun showEmailError() {
        binding.pbLoading.hide()
        binding.email.error = getString(R.string.login_email_error_message)
    }

    private fun showPasswordError() {
        binding.pbLoading.hide()
        binding.pwd.error = getString(R.string.login_password_error_message)
    }

    private fun showSnackError() {
        binding.pbLoading.hide()
        Snackbar.make(binding.root, R.string.login_error_message, Snackbar.LENGTH_LONG).show()
    }

    private fun showHome() {
        binding.pbLoading.hide()
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showNovo(){
        binding.pbLoading.hide()
        val intent = Intent (this@LoginActivity, ProfileActivity::class.java)
        startActivity(intent)

    }

}