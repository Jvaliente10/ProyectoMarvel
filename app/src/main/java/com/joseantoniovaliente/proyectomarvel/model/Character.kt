package com.joseantoniovaliente.proyectomarvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Character(val name: String, val url: String): Parcelable  {
}