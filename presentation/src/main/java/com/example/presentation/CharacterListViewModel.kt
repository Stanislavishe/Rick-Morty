package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.RMRepository
import com.example.domain.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repo: RMRepository
) : ViewModel() {

    val charactersList: Flow<PagingData<Character>> =
        Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { CharactersPagingSource(repo) }
        ).flow.cachedIn(viewModelScope)
}