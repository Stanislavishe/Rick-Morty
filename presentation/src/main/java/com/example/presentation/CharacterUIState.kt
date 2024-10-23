package com.example.presentation

import com.example.domain.models.Character
import com.example.domain.models.Episode

interface CharacterUIState {

    data class SuccessLoadCharacter(val character: Character, val episodes: List<Episode>) :
        CharacterUIState

//    data class SuccessLoadEpisode(val episodes: List<Episode>) : CharacterUIState

    data class Error(private val error: String) : CharacterUIState

    object Initial : CharacterUIState
}