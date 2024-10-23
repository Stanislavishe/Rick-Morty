package com.example.domain

import com.example.domain.models.Character
import com.example.domain.models.Episode

interface RMRepository {

    suspend fun getCharacterList(page: Int): List<Character>

    suspend fun getSinglePerson(id: Int) : LoadCharacterResult

//    suspend fun getSingleEpisode(id: String): LoadCharacterResult
}