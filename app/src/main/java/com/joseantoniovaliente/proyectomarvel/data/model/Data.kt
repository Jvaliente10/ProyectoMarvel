package com.joseantoniovaliente.proyectomarvel.data.model

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<MCharacterResult>,
    val total: Int
)