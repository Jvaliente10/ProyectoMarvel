package com.joseantoniovaliente.proyectomarvel.ui.viewModel
import androidx.lifecycle.*
import com.joseantoniovaliente.proyectomarvel.data.model.MCharacterResponse
import com.joseantoniovaliente.proyectomarvel.data.model.MCharacterResult
import com.joseantoniovaliente.proyectomarvel.data.repository.MCharacterRepository
import com.joseantoniovaliente.proyectomarvel.utils.Resource


import kotlinx.coroutines.*
import retrofit2.Response

class MCharacterViewModel(val mCharacterRepository: MCharacterRepository) : ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)

        }
    }
    fun getMCharacters() = viewModelScope.launch {
        _state.postValue(Resource.Loading())
        val response = mCharacterRepository.getMCharacters()
        _state.postValue(response(response))
    }
    private fun handleResponse(response: Response<MCharacterResponse>): Resource<MCharacterResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    fun navigateTo(mCharacterResult: MCharacterResult) {
        _state.value = _state.value?.copy(navigateTo = mCharacterResult)
    }
    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }
    data class UiState(
        val loading: Boolean = false,
        val characters: List<Character>? = null,
        val navigateTo: MCharacterResult? = null
    )
}