package com.example.quintoplayerapp.perfil.avaliacaoRegister.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.module.components.hide
import com.example.module.components.show
import com.example.quintoplayerapp.R
import com.example.quintoplayerapp.databinding.ActivityRegisterAvaliacaoBinding
import com.example.quintoplayerapp.perfil.avaliacao.presentation.model.AvaliacaoViewState
import com.example.quintoplayerapp.perfil.avaliacaoRegister.presentation.AvaliacaoRegisterViewModel
import com.google.android.material.snackbar.Snackbar

class AvaliacaoRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterAvaliacaoBinding

    private val viewModel: AvaliacaoRegisterViewModel by viewModels();


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityRegisterAvaliacaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeObserver()

        validateData()

    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                AvaliacaoViewState.ShowHomeScreen -> showHome()
                AvaliacaoViewState.ShowAvaliacaoError -> showAvaliacaoError()
                AvaliacaoViewState.ShowNotaError -> showNotaError()
                AvaliacaoViewState.ShowMessageError -> showSnackError()
                AvaliacaoViewState.ShowLoading -> showLoading()
            }
        }
    }

    private fun showLoading() {
        binding.pbLoading.show()
    }

    private fun showAvaliacaoError() {
        binding.pbLoading.hide()
        binding.inserirAvaliacao.error = getString(R.string.register_descricao_error_message)
    }

    private fun showNotaError() {
        binding.pbLoading.hide()
        binding.inserirNota.error = getString(R.string.register_endereco_error_message)
    }


    private fun showHome() {
        binding.pbLoading.hide()
        finish()
    }

    private fun showSnackError() {
        binding.pbLoading.hide()
        Snackbar.make(binding.root, R.string.erro_message, Snackbar.LENGTH_LONG).show()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun validateData() {
        binding.registerButton.setOnClickListener {
            val notaString = binding.inserirNota.text.toString()

            val idUsuarioDestinatario = intent.extras?.getInt("idUsuario")

            if (idUsuarioDestinatario != null) {
                val notaInt = notaString.toIntOrNull()

                if (notaInt != null) {
                    viewModel.validateInputs(
                        avaliacao = binding.inserirAvaliacao.text.toString(),
                        nota = notaInt,
                        idUsuario = idUsuarioDestinatario.toInt()
                    )
                } else {
                    showNotaError()
                }
            } else {
                showSnackError()
            }
        }
    }
}


