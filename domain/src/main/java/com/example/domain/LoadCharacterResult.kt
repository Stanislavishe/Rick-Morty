package com.example.domain

import com.example.domain.models.Character

interface LoadCharacterResult {

    fun <T: Any> map(mapper: Mapper<T>) : T

    interface Mapper<T: Any> {
        fun mapSingleSuccess(character: Character) : T

        fun mapListSuccess(list: List<Character>) : T

        fun mapError(error: String) : T
    }

    data class SuccessSingleCharacter(private val character: Character) : LoadCharacterResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapSingleSuccess(character)
        }
    }

    data class SuccessCharacterList(private val list: List<Character>) : LoadCharacterResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapListSuccess(list)
        }
    }

    data class Error(private val error: String) : LoadCharacterResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapError(error)
        }
    }
}