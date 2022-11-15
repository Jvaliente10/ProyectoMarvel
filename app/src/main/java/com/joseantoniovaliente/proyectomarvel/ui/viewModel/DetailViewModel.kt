package com.joseantoniovaliente.proyectomarvel.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joseantoniovaliente.proyectomarvel.data.repository.MCharacterRepository
import com.joseantoniovaliente.proyectomarvel.ui.viewModel.MCharacterViewModel

class DetailViewModel(
    val mCharacterRepository: MCharacterRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MCharacterViewModel(mCharacterRepository) as T
    }
}