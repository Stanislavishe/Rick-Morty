package com.example.data

import com.example.domain.LoadCharacterResult
import com.example.domain.RMRepository
import com.example.domain.models.Character
import javax.inject.Inject

class RMRepositoryImpl @Inject constructor(
    private val api: RMApi
): RMRepository {
    override suspend fun getCharacterList(page: Int): List<Character> {
        return try {
            api.personsList(page).results
        } catch (e: Exception) {
            emptyList<Character>()
        }
    }

    override suspend fun getSinglePerson(id: Int): LoadCharacterResult {
        return try {
            val character = api.singlePerson(id.toString())
            LoadCharacterResult.SuccessSingleCharacter(character)
        } catch (e: Exception) {
            LoadCharacterResult.Error(e.message ?: "")
        }
    }

}