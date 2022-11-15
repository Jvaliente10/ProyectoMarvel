package com.joseantoniovaliente.proyectomarvel.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseantoniovaliente.proyectomarvel.R
import com.joseantoniovaliente.proyectomarvel.databinding.ViewCharacterBinding
import com.joseantoniovaliente.proyectomarvel.ui.inflate
import com.joseantoniovaliente.proyectomarvel.ui.loadUrl


class CharacterAdapter(val listener: (Character) -> Unit):
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    var characters = emptyList<Character>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_character, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)

        holder.itemView.setOnClickListener {
            listener(character)
        }
    }

    override fun getItemCount(): Int = characters.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ViewCharacterBinding.bind(view)
        fun bind(character: Character){
            binding.title.text = character.name

            binding.imagen.loadUrl(character.url)
        }
    }
}