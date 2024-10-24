package com.example.presentation.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.LoadCharacterResult
import com.example.domain.RMRepository
import com.example.domain.models.Character
import com.example.domain.models.Episode
import com.example.presentation.CharacterUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleCharacterViewModel  @Inject constructor(
    private val repo: RMRepository,
    private val mapper: LoadCharacterResult.Mapper<CharacterUIState>
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterUIState>(CharacterUIState.Initial)
    val uiState = _uiState.asStateFlow()

    fun loadPerson(id: Int) {
        viewModelScope.launch {
            val uiState = repo.getSinglePerson(id).map(mapper)
            _uiState.value = uiState
        }
    }

}