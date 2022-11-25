package com.joseantoniovaliente.proyectomarvel.data.model

import kotlinx.parcelize.RawValue

@RawValue
data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)