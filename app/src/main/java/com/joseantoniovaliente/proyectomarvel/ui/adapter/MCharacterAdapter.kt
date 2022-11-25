package com.joseantoniovaliente.proyectomarvel.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.joseantoniovaliente.proyectomarvel.R
import com.joseantoniovaliente.proyectomarvel.data.model.MCharacterResult
import com.joseantoniovaliente.proyectomarvel.databinding.ItemCharacterBinding
import com.joseantoniovaliente.proyectomarvel.utils.inflate
import com.joseantoniovaliente.proyectomarvel.utils.loadUrl


class MCharacterAdapter(val listener: (MCharacterResult) -> Unit):
    RecyclerView.Adapter<MCharacterAdapter.ViewHolder>() {

    var mCharacters = emptyList<MCharacterResult>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_character, false)
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
        private val binding = ItemCharacterBinding.bind(view)
        fun bind(mCharacterResult: MCharacterResult){

            Glide.with(this)
                .load("${mCharacterResult.thumbnail?.path}.${mCharacterResult.thumbnail?.extension}")
                .apply(requestOptions)
                .placeholder(R.drawable.image)
                .into(rv_image)
        }
    }
}