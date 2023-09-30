package com.example.quintoplayerapp.perfil.avaliacaoRegister.view

import androidx.recyclerview.widget.RecyclerView
import com.example.quintoplayerapp.databinding.AvaliacoesListItemBinding
import com.example.quintoplayerapp.perfil.avaliacao.data.local.AvaliacaoModel

class AvaliacaoViewHolder(
    private val binding: AvaliacoesListItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(avaliacao: AvaliacaoModel) {
        binding.tvAvaliacao.text = avaliacao.avaliacao
        binding.tvNota.text = avaliacao.nota.toString()

    }
}