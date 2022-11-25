package com.joseantoniovaliente.proyectomarvel.data.api

import com.joseantoniovaliente.proyectomarvel.data.model.MCharacterResponse
import com.joseantoniovaliente.proyectomarvel.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMCharacterService {

    @GET("characters?events=270")
    fun getMCharacters(
        @Query("ts") apiKey: String = Constant.ts,
        @Query("apikey") ts: String = Constant.API_KEY,
        @Query("hash") hash: String = Constant.hash(),
        @Query("limit") limit: String = Constant.limit
    ): Response<MCharacterResponse>


}