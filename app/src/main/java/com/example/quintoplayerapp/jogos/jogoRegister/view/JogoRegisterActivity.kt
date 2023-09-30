package com.example.quintoplayerapp.jogos.jogoRegister.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.module.components.hide
import com.example.module.components.show
import com.example.quintoplayerapp.R
import com.example.quintoplayerapp.databinding.ActivityRegisterPartidaBinding
import com.example.quintoplayerapp.jogos.jogo.presentation.JogoViewState
import com.example.quintoplayerapp.jogos.jogoRegister.presentation.JogoRegisterViewModel
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class JogoRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPartidaBinding
    private lateinit var dataEHorario: String
    private val viewModel: JogoRegisterViewModel by viewModels();

    @RequiresApi(Build.VERSION_CODES.O)
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityRegisterPartidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeObserver()

        validateData()

        binding.dateAndTimeEditTextPartida.setOnClickListener {
            showDateTimePicker()
        }

    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                JogoViewState.ShowHomeScreen -> showHome()
                JogoViewState.ShowDescricaoError -> showDescricaoError()
                JogoViewState.ShowDataError -> showDataError()
                JogoViewState.ShowLocalError -> showEnderecoError()
                JogoViewState.ShowMessageError -> showSnackError()
                JogoViewState.ShowLoading -> showLoading()
            }
        }
    }

    private fun showLoading() {
        binding.pbLoading.show()
    }

    private fun showDescricaoError() {
        binding.pbLoading.hide()
        binding.inserirDescricao.error = getString(R.string.register_descricao_error_message)
    }

    private fun showEnderecoError() {
        binding.pbLoading.hide()
        binding.inserirEndereco.error = getString(R.string.register_endereco_error_message)
    }

    private fun showDataError() {
        binding.pbLoading.hide()
        binding.dateAndTimeEditTextPartida.error =
            getString(R.string.register_dataEHorario_error_message)
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
            viewModel.validateInputs(
                descricao = binding.inserirDescricao.text.toString(),
                enderecoDoJogo = binding.inserirEndereco.text.toString(),
                dataEHorario = binding.dateAndTimeEditTextPartida.text.toString()

            )
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDateTimePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val dateTimePickerDialog = DatePickerDialog(
            this,
            { _, pickedYear, pickedMonth, pickedDay ->
                val timePickerDialog = TimePickerDialog(
                    this,
                    { _, pickedHour, pickedMinute ->
                        val selectedDateTime = LocalDateTime.of(
                            pickedYear,
                            pickedMonth + 1,
                            pickedDay,
                            pickedHour,
                            pickedMinute
                        )
                        dataEHorario = selectedDateTime.format(dateFormatter)


                        binding.dateAndTimeEditTextPartida.setText(dataEHorario)
                    },
                    hour, minute, true
                )
                timePickerDialog.show()
            },
            year, month, day
        )
        dateTimePickerDialog.show()
    }
}
