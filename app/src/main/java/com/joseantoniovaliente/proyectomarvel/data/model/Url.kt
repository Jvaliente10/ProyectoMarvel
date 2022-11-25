package com.joseantoniovaliente.proyectomarvel.data.model


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Url(
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
) : Parcelable