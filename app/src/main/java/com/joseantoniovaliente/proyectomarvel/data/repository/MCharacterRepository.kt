package com.joseantoniovaliente.proyectomarvel.data.repository

import com.joseantoniovaliente.proyectomarvel.data.api.RemoteConnection

class MCharacterRepository {
    fun getMCharacters() = RemoteConnection.service.getMCharacters()
}