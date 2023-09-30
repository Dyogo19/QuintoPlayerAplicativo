package com.example.quintoplayerapp.profile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.module.components.hide
import com.example.module.components.show
import com.example.quintoplayerapp.R
import com.example.quintoplayerapp.databinding.ActivityProfileBinding
import com.example.quintoplayerapp.profile.model.ProfileViewState
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initializeObserver()

        binding.registrar.setOnClickListener {
            viewModel.validateInputsRegistrer(
                senha = binding.pwd.text.toString(),
                email = binding.email.text.toString(),
                nome = binding.inserirNome.text.toString(),
                numeroDeCelular = binding.inserirNumero.text.toString()
            )
        }

    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                ProfileViewState.ShowHomeScreen -> showHome()
                ProfileViewState.ShowErrorMessage -> showSnackError()
                ProfileViewState.ShowEmailErrorMessage -> showEmailError()
                ProfileViewState.ShowPasswordErrorMessage -> showPasswordError()
                ProfileViewState.ShowLoading -> showLoading()
                ProfileViewState.ShowNameErrorMessage -> showNameError()
                ProfileViewState.ShowNumberErrorMessage -> showNumberError()
                ProfileViewState.ShowSuccesCreate -> showSuccesCreate()

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

    private fun showNameError() {
        binding.pbLoading.hide()
        binding.inserirNome.error = getString(R.string.login_name_error_message)
    }

    private fun showNumberError() {
        binding.pbLoading.hide()
        binding.inserirNumero.error = getString(R.string.login_number_error_message)
    }

    private fun showSnackError() {
        binding.pbLoading.hide()
        Snackbar.make(binding.root, R.string.login_error_message, Snackbar.LENGTH_LONG).show()
    }

    private fun showHome() {
        binding.pbLoading.hide()
        finish()
    }

    private fun showSuccesCreate() {
        binding.pbLoading.hide()
        Snackbar.make(binding.root, R.string.register_user_succes, Snackbar.LENGTH_LONG).show()
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, 1000)
    }

}

