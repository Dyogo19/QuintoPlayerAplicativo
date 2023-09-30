package com.example.quintoplayerapp.jogos.jogoRegister.view

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.RecyclerView
import com.example.quintoplayerapp.App.Companion.context
import com.example.quintoplayerapp.databinding.ActivityJogoBinding
import com.example.quintoplayerapp.databinding.PartidaListItemBinding
import com.example.quintoplayerapp.jogos.jogo.data.local.JogoModel
import com.example.quintoplayerapp.perfil.PerfilActivity
import com.example.quintoplayerapp.perfil.PerfilPostadorActivity
import kotlin.math.log

class JogoListAdapter : RecyclerView.Adapter<JogoViewHolder>() {
    private val list: MutableList<JogoModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogoViewHolder {
        val binding = PartidaListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JogoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: JogoViewHolder, position: Int) {
        val jogoModel = list[position]
        holder.bind(jogoModel)

        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.itemView.context, PerfilPostadorActivity::class.java)
            intent.putExtra("nome", jogoModel.nome)
            intent.putExtra("numeroDeCelular", jogoModel.numeroDeCelular)
            intent.putExtra("idUsuario", jogoModel.idUsuario)
            holder.itemView.context.startActivity(intent)
        }
    }

    fun add(items: List<JogoModel>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}