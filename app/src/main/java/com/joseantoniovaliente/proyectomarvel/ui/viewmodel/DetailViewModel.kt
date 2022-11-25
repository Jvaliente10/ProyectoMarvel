package com.joseantoniovaliente.proyectomarvel.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModel(mCharacterResult: MCharacterResult): ViewModel() {
    private val _mCharacterResult = MutableLiveData(mCharacterResult)
    val mCharacter: LiveData<MCharacterResult> get() = _mCharacterResult
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val mCharacterResult: MCharacterResult): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(mCharacterResult) as T
    }

}