package com.example.quintoplayerapp.jogos.jogoRegister.view

import androidx.recyclerview.widget.RecyclerView
import com.example.quintoplayerapp.databinding.PartidaListItemBinding
import com.example.quintoplayerapp.jogos.jogo.data.local.JogoModel

class JogoViewHolder(
    val binding: PartidaListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(jogo: JogoModel) {
        binding.tvDescricao.text = jogo.descricao
        binding.tvDataEHora.text = jogo.dataEHorario
        binding.tvEnderecoDoJogo.text = jogo.enderecoDoJogo

    }
}