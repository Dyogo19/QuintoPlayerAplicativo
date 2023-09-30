package com.example.quintoplayerapp.jogos.jogoRegister.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quintoplayerapp.databinding.ActivityJogoBinding
import com.example.quintoplayerapp.home.HomeActivity
import com.example.quintoplayerapp.jogos.jogo.data.local.JogoModel
import com.example.quintoplayerapp.perfil.PerfilActivity

class JogoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJogoBinding
    private val viewModel: JogoViewModel by viewModels()
    private val jogoListAdapter by lazy {
        JogoListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityJogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvList.adapter = jogoListAdapter

        initializeObserver()
        navigateNewJogo()
        navigateHome()
    }

    override fun onResume() {
        super.onResume()
        viewModel.listJogo()
    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                is JogoViewStateList.ShowHomeScreen -> showHomeScreen(viewState.list)
                JogoViewStateList.ShowEmptyList -> showEmptyList()
                JogoViewStateList.ShowLoading -> showLoading()
            }
        }
    }

    private fun showHomeScreen(list: List<JogoModel>) {
        jogoListAdapter.add(list)
    }

    fun showEmptyList() {

    }

    fun showLoading() {

    }

    private fun navigateNewJogo() {
        binding.btNewJogo.setOnClickListener {

            val intent = Intent(this@JogoActivity, JogoRegisterActivity::class.java)
            startActivity(intent)
        }
    }


    private fun navigateHome() {
        binding.btHome.setOnClickListener {

            val intent = Intent(this@JogoActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

}