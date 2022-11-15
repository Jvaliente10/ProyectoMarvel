package com.joseantoniovaliente.proyectomarvel.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joseantoniovaliente.proyectomarvel.model.Character

class DetailViewModel(character:Character): ViewModel() {
    private val _character = MutableLiveData(character)
    val character: LiveData<Character> get() = _character
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val character: Character): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(character) as T
    }

}