package com.joseantoniovaliente.proyectomarvel.ui.main

import androidx.lifecycle.*
import com.joseantoniovaliente.proyectomarvel.model.Character
import com.joseantoniovaliente.proyectomarvel.model.CharacterProvider
import kotlinx.coroutines.*

class MainViewModel(): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            val characters =  withContext(Dispatchers.IO){CharacterProvider.getCharacters()}
            _state.value = _state.value?.copy(loading = false, characters = characters)
        }
    }
    fun navigateTo(character: Character) {
        _state.value = _state.value?.copy(navigateTo = character)
    }
    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }
    data class UiState(
        val loading: Boolean = false,
        val characters: List<Character>? = null,
        val navigateTo: Character? = null
    )
}