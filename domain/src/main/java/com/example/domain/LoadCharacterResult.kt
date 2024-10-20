package com.example.domain

import com.example.domain.models.Character
import com.example.domain.models.Episode

interface LoadCharacterResult {

    fun <T: Any> map(mapper: Mapper<T>) : T

    interface Mapper<T: Any> {
        fun mapSingleSuccess(character: Character) : T

        fun mapSingleEpisode(episodes: List<Episode>): T

        fun mapError(error: String) : T
    }

    data class SuccessSingleCharacter(private val character: Character) : LoadCharacterResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapSingleSuccess(character)
        }
    }

    data class SuccessSingleEpisode(private val episodes: List<Episode>) : LoadCharacterResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapSingleEpisode(episodes)
        }
    }

    data class Error(private val error: String) : LoadCharacterResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapError(error)
        }
    }
}