package com.joseantoniovaliente.proyectomarvel.ui.viewmodel
import androidx.lifecycle.*
import com.joseantoniovaliente.proyectomarvel.data.repository.MCharacterRepository
import com.joseantoniovaliente.proyectomarvel.utils.Resource
import kotlinx.coroutines.*
import retrofit2.Response

class MainViewModel(val mCharacterRepository: MCharacterRepository
):ViewModel(){

    val mcharacters: MutableLiveData<Resource<MCharacterResponse>> = MutableLiveData()

    init {
        getComics()
    }
    fun getComics() = viewModelScope.launch {
        mcharacters.postValue(Resource.Loading())
        val response = mCharacterRepository.getMCharacters()
        mcharacters.postValue(handleResponse(response))
    }

    private fun handleResponse(response: Response<MCharacterResponse>): Resource<MCharacterResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}