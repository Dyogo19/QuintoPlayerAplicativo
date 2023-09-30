package com.example.quintoplayerapp.perfil

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.quintoplayerapp.R
import com.example.quintoplayerapp.database.FHDatabase
import com.example.quintoplayerapp.databinding.ActivityPerfilPostadorBinding
import com.example.quintoplayerapp.home.HomeActivity
import com.example.quintoplayerapp.perfil.avaliacao.data.local.AvaliacaoModel
import com.example.quintoplayerapp.perfil.avaliacaoRegister.view.AvaliacaoListAdapter
import com.example.quintoplayerapp.perfil.avaliacaoRegister.view.AvaliacaoRegisterActivity
import com.example.quintoplayerapp.perfil.avaliacaoRegister.view.AvaliacaoViewModel
import com.example.quintoplayerapp.perfil.avaliacaoRegister.view.AvaliacaoViewStateList

class PerfilPostadorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilPostadorBinding
    private val viewModel: AvaliacaoViewModel by viewModels()
    private val avaliacaoListAdapter by lazy {
        AvaliacaoListAdapter()
    }

    private val idUsuario by lazy{
        intent.extras?.getInt("idUsuario")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityPerfilPostadorBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initializeObserver()
        navigateNewAvaliacao()
        navigateHome()
        nomeEContato()
        mensagem()

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

            val intent = Intent(this@PerfilPostadorActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateNewAvaliacao() {
        binding.btNewAvaliacao.setOnClickListener {
            val intent = Intent(this, AvaliacaoRegisterActivity::class.java)
            intent.putExtra("idUsuario", idUsuario)
            startActivity(intent)
        }
    }

    private val database: FHDatabase by lazy {
        FHDatabase.getInstance()
    }

    private fun nomeEContato() {

        val nome = intent.getStringExtra("nome")
        val numero = intent.getStringExtra("numeroDeCelular")

        val nomeTextView = findViewById<TextView>(R.id.nomeTextView)
        val numeroTextView = findViewById<TextView>(R.id.numeroTextView)


        nomeTextView.text = "Nome: $nome"
        numeroTextView.text = "Número de Contato: $numero"
    }

    private fun mensagem() {
        binding.btnSendMessage.setOnClickListener {
            val nome = database.loginDao().getNome()
            val numero = database.loginDao().getNumero()

            val message = "Olá, $nome! Estou enviando uma mensagem do QuintoPlayer!."

            val uri = Uri.parse("https://api.whatsapp.com/send?phone=$numero&text=$message")

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}
