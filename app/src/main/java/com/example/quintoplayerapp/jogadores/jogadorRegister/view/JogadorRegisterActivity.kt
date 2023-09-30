package com.example.quintoplayerapp.jogadores.jogadorRegister.view

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
import com.example.quintoplayerapp.databinding.ActivityRegisterPlayerBinding
import com.example.quintoplayerapp.jogadores.jogador.presentation.JogadorViewState
import com.example.quintoplayerapp.jogadores.jogadorRegister.presentation.JogadorRegisterViewModel
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class JogadorRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPlayerBinding
    private lateinit var dataEHoraLivrePraJogar: String
    private val viewModel: JogadorRegisterViewModel by viewModels();

    @RequiresApi(Build.VERSION_CODES.O)
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityRegisterPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeObserver()

        validateData()

        binding.dateAndTimeEditTextPlayer.setOnClickListener {
            showDateTimePicker()
        }

    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                JogadorViewState.ShowHomeScreen -> showHome()
                JogadorViewState.ShowDataError -> showDataError()
                JogadorViewState.ShowNomeError -> showNomeError()
                JogadorViewState.ShowMessageError -> showSnackError()
                JogadorViewState.ShowLoading -> showLoading()
            }
        }
    }

    private fun showLoading() {
        binding.pbLoading.show()
    }


    private fun showDataError() {
        binding.pbLoading.hide()
        binding.dateAndTimeEditTextPlayer.error =
            getString(R.string.register_dataEHorario_error_message)
    }

    private fun showNomeError() {
        binding.pbLoading.hide()
        binding.inserirJogador.error = getString(R.string.register_namejogador_error_message)
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
                nomeJogador = binding.inserirJogador.text.toString(),
                dataEHoraLivrePraJogar = binding.dateAndTimeEditTextPlayer.text.toString()

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
                        dataEHoraLivrePraJogar = selectedDateTime.format(dateFormatter)


                        binding.dateAndTimeEditTextPlayer.setText(dataEHoraLivrePraJogar)
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
