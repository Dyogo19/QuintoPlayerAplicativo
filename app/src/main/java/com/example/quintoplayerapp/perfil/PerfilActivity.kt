package com.example.quintoplayerapp.perfil

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.quintoplayerapp.R
import com.example.quintoplayerapp.database.FHDatabase
import com.example.quintoplayerapp.databinding.ActivityPerfilBinding
import com.example.quintoplayerapp.home.HomeActivity
import com.example.quintoplayerapp.jogos.jogo.data.local.JogoModel
import com.example.quintoplayerapp.jogos.jogoRegister.view.JogoRegisterActivity
import com.example.quintoplayerapp.jogos.jogoRegister.view.JogoViewStateList
import com.example.quintoplayerapp.login.data.LoginResponse
import com.example.quintoplayerapp.login.data.UserRequest
import com.example.quintoplayerapp.login.data.UserResponse
import com.example.quintoplayerapp.login.data.local.LoginEntity
import com.example.quintoplayerapp.perfil.avaliacao.data.local.AvaliacaoModel
import com.example.quintoplayerapp.perfil.avaliacaoRegister.view.AvaliacaoListAdapter
import com.example.quintoplayerapp.perfil.avaliacaoRegister.view.AvaliacaoRegisterActivity
import com.example.quintoplayerapp.perfil.avaliacaoRegister.view.AvaliacaoViewModel
import com.example.quintoplayerapp.perfil.avaliacaoRegister.view.AvaliacaoViewStateList
import com.example.quintoplayerapp.profile.ProfileActivity

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding
    private val viewModel: AvaliacaoViewModel by viewModels()
    private val avaliacaoListAdapter by lazy {
        AvaliacaoListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvList.adapter = avaliacaoListAdapter

        initializeObserver()
        navigateHome()
        nomeEContato()

    }

    override fun onResume() {
        super.onResume()
        viewModel.listAvaliacao()
    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                is AvaliacaoViewStateList.ShowHomeScreen -> showHomeScreen(viewState.list)
                AvaliacaoViewStateList.ShowEmptyList -> showEmptyList()
                AvaliacaoViewStateList.ShowLoading -> showLoading()
            }
        }
    }

    private fun showHomeScreen(list: List<AvaliacaoModel>) {
        avaliacaoListAdapter.add(list)
    }

    fun showEmptyList() {

    }

    fun showLoading() {

    }

    private fun navigateHome() {
        binding.btHome.setOnClickListener {

            val intent = Intent(this@PerfilActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private val database: FHDatabase by lazy {
        FHDatabase.getInstance()
    }

    private fun nomeEContato() {
        val user = database.loginDao().getLogin()
        val nome = database.loginDao().getNome()
        val numero = database.loginDao().getNumero()

        val nomeTextView = findViewById<TextView>(R.id.nomeTextView)
        val numeroTextView = findViewById<TextView>(R.id.numeroTextView)

        nomeTextView.text = "Nome: $nome"
        numeroTextView.text = "Número de Contato: $numero"

    }
}
