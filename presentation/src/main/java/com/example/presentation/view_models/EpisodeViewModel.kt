package com.example.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.LoadCharacterResult
import com.example.domain.RMRepository
import com.example.presentation.CharacterUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repo: RMRepository,
    private val mapper: LoadCharacterResult.Mapper<CharacterUIState>
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterUIState>(CharacterUIState.Initial)
    val uiState = _uiState.asStateFlow()

    fun loadEpisode(id: Int) {
        viewModelScope.launch {
            val episode = repo.getSingleEpisode(id.toString()).map(mapper)
            _uiState.value = episode
        }
    }
}