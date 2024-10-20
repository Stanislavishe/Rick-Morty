package com.example.domain

import com.example.domain.models.Character

interface RMRepository {

    suspend fun getCharacterList(page: Int): List<Character>

    suspend fun getSinglePerson(id: Int) : LoadCharacterResult
}