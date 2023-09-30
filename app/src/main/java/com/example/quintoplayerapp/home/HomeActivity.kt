package com.example.quintoplayerapp.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.quintoplayerapp.databinding.ActivityHomeBinding
import com.example.quintoplayerapp.jogadores.jogadorRegister.view.JogadorActivity
import com.example.quintoplayerapp.jogos.jogoRegister.view.JogoActivity
import com.example.quintoplayerapp.perfil.PerfilActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btJogadores.setOnClickListener {
            val intent = Intent(this@HomeActivity, JogadorActivity::class.java)
            startActivity(intent)
        }

        binding.btPartidas.setOnClickListener {
            val intent = Intent(this@HomeActivity, JogoActivity::class.java)
            startActivity(intent)
        }

        binding.btPerfil.setOnClickListener {
            val intent = Intent(this@HomeActivity, PerfilActivity :: class.java)
            startActivity(intent)
        }


    }
}



