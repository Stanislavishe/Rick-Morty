package com.example.data

import com.example.domain.models.Character
import com.example.domain.models.CharacterModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RMApi {
    @GET("character")
    suspend fun personsList(
        @Query("page") page: Int
    ) : CharacterModel

    @GET("character/{id}")
    suspend fun singlePerson(
        @Path("id") id: String
    ) : Character
}