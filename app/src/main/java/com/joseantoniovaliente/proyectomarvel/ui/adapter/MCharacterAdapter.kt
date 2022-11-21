package com.joseantoniovaliente.proyectomarvel.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseantoniovaliente.proyectomarvel.R
import com.joseantoniovaliente.proyectomarvel.data.model.MCharacterResult
import com.joseantoniovaliente.proyectomarvel.databinding.ViewMcharacterBinding
import com.joseantoniovaliente.proyectomarvel.ui.inflate
import com.joseantoniovaliente.proyectomarvel.ui.loadUrl


class MCharacterAdapter(val listener: (MCharacterResult) -> Unit):
    RecyclerView.Adapter<MCharacterAdapter.ViewHolder>() {

    var mCharacters = emptyList<MCharacterResult>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_mcharacter, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mCharacter = mCharacters[position]
        holder.bind(mCharacter)

        holder.itemView.setOnClickListener {
            listener(mCharacter)
        }
    }

    override fun getItemCount(): Int = mCharacters.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ViewMcharacterBinding.bind(view)
        fun bind(mCharacter: MCharacterResult){
            binding.name.text = mCharacter.name

            binding.imageElement.loadUrl(mCharacter.urls)
        }
    }
}