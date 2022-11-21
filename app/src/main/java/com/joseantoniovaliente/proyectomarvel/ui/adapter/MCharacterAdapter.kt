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



class MCharacterAdapter(): RecyclerView.Adapter<MCharacterAdapter.MCharacterViewModel>(){


    inner class MCharacterViewModel(itemView: View):RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<MCharacterResult> (){
        override fun areItemsTheSame(oldItem: MCharacterResult, newItem: MCharacterResult): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: MCharacterResult, newItem: MCharacterResult): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MCharacterViewModel {
        return MCharacterViewModel(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_mcharacter,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MCharacterViewModel, position: Int) {
        val character = differ.currentList[position]
        holder.itemView.apply {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.image)
            requestOptions.error(R.drawable.image)
            Glide.with(this)
                .load("${character.thumbnail?.path}.${character.thumbnail?.extension}")
                .apply(requestOptions)
                .placeholder(R.drawable.image)
                .into()

            setOnClickListener {
                onItemClickListener?.let { it(character) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((MCharacterResult) -> Unit)? = null

    fun setOnItemClicklistener(listener: (MCharacterResult) -> Unit){
        onItemClickListener = listener
    }
}