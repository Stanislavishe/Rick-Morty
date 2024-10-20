package com.example.presentation

import com.example.domain.LoadCharacterResult
import com.example.domain.models.Character
import com.example.domain.models.Episode
import javax.inject.Inject

class CharacterUIMapper @Inject constructor(): LoadCharacterResult.Mapper<CharacterUIState> {
    override fun mapSingleSuccess(character: Character): CharacterUIState {
        return CharacterUIState.SuccessLoadCharacter(character)
    }

    override fun mapError(error: String): CharacterUIState {
        return CharacterUIState.Error(error)
    }

    override fun mapSingleEpisode(episodes: List<Episode>): CharacterUIState {
        return CharacterUIState.SuccessLoadEpisode(episodes)
    }
}