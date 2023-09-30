package com.example.quintoplayerapp.jogadores.jogadorRegister.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.quintoplayerapp.databinding.ActivityJogadorBinding
import com.example.quintoplayerapp.home.HomeActivity
import com.example.quintoplayerapp.jogadores.jogador.data.local.JogadorModel

class JogadorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJogadorBinding
    private val viewModel: JogadorViewModel by viewModels()
    private val jogadorListAdapter by lazy {
        JogadorListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityJogadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvList.adapter = jogadorListAdapter

        initializeObserver()
        navigateNewJogador()
        navigateHome()
    }

    override fun onResume() {
        super.onResume()
        viewModel.listJogador()
    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                is JogadorViewStateList.ShowHomeScreen -> showHomeScreen(viewState.list)
                JogadorViewStateList.ShowEmptyList -> showEmptyList()
                JogadorViewStateList.ShowLoading -> showLoading()
            }
        }
    }

    private fun showHomeScreen(list: List<JogadorModel>) {
        jogadorListAdapter.add(list)
    }

    fun showEmptyList() {

    }

    fun showLoading() {

    }


    private fun navigateNewJogador() {
        binding.btNewJogador.setOnClickListener {

            val intent = Intent(this@JogadorActivity, JogadorRegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun navigateHome() {
        binding.btHome.setOnClickListener {

            val intent = Intent(this@JogadorActivity, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}