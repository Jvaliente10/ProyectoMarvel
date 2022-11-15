package com.joseantoniovaliente.proyectomarvel.data.model

import com.google.gson.annotations.SerializedName
import com.joseantoniovaliente.proyectomarvel.data.model.Data

data class MCharacterResponse(
val copyright:String,
@SerializedName("data")
val data: Data?= null
)