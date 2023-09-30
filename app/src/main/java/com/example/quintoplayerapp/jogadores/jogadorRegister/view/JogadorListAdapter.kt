package com.example.quintoplayerapp.jogadores.jogadorRegister.view

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quintoplayerapp.databinding.JogadorListItemBinding
import com.example.quintoplayerapp.jogadores.jogador.data.local.JogadorModel
import com.example.quintoplayerapp.perfil.PerfilPostadorActivity

class JogadorListAdapter : RecyclerView.Adapter<JogadorViewHolder>() {

    private val list: MutableList<JogadorModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogadorViewHolder {
        val binding =
            JogadorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JogadorViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: JogadorViewHolder, position: Int) {
        val jogadorModel = list[position]
        holder.bind(jogadorModel)

        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.itemView.context, PerfilPostadorActivity::class.java)
            intent.putExtra("nome", jogadorModel.nome)
            intent.putExtra("numeroDeCelular", jogadorModel.numeroDeCelular)
            intent.putExtra("idUsuario", jogadorModel.idUsuario)
            holder.itemView.context.startActivity(intent)
        }
    }

    fun add(items: List<JogadorModel>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

}