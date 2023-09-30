package com.example.quintoplayerapp.perfil.avaliacaoRegister.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quintoplayerapp.databinding.AvaliacoesListItemBinding
import com.example.quintoplayerapp.perfil.avaliacao.data.local.AvaliacaoModel

class AvaliacaoListAdapter : RecyclerView.Adapter<AvaliacaoViewHolder>() {

    private val list: MutableList<AvaliacaoModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvaliacaoViewHolder {
        val binding =
            AvaliacoesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AvaliacaoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AvaliacaoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun add(items: List<AvaliacaoModel>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

}