package com.joseantoniovaliente.proyectomarvel.data.repository

import com.joseantoniovaliente.proyectomarvel.data.api.RemoteConection

class MCharacterRepository {
    fun getMCharacters() = RemoteConection.api.getMCharacters()
}