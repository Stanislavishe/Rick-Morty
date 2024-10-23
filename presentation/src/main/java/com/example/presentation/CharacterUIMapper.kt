package com.example.presentation

import com.example.domain.LoadCharacterResult
import com.example.domain.models.Character
import com.example.domain.models.Episode
import javax.inject.Inject

class CharacterUIMapper @Inject constructor(): LoadCharacterResult.Mapper<CharacterUIState> {
    override fun mapSingleSuccess(character: Character, episodes: List<Episode>): CharacterUIState {
        return CharacterUIState.SuccessLoadCharacter(character, episodes)
    }

    override fun mapError(error: String): CharacterUIState {
        return CharacterUIState.Error(error)
    }

    override fun mapSingleEpisode(episode: Episode, persons: List<Character>): CharacterUIState {
        return CharacterUIState.SuccessLoadEpisode(episode, persons)
    }
}