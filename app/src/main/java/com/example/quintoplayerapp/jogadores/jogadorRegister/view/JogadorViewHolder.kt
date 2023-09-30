package com.example.quintoplayerapp.jogadores.jogadorRegister.view

import androidx.recyclerview.widget.RecyclerView
import com.example.quintoplayerapp.databinding.JogadorListItemBinding
import com.example.quintoplayerapp.databinding.PartidaListItemBinding
import com.example.quintoplayerapp.jogadores.jogador.data.local.JogadorModel
import com.example.quintoplayerapp.jogos.jogo.data.local.JogoModel

class JogadorViewHolder(
    val binding: JogadorListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(jogador: JogadorModel) {
        binding.tvNomeJogador.text = jogador.nomeJogador
        binding.tvDataEHoraJogador.text = jogador.dataEHoraLivrePraJogar


    }
}