package com.example.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.domain.RMRepository
import com.example.presentation.CharactersPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repo: RMRepository
) : ViewModel() {

    val charactersList =
        Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { CharactersPagingSource(repo) }
        ).flow.cachedIn(viewModelScope)

}