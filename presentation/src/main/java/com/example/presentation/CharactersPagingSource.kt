package com.example.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.RMRepository
import com.example.domain.models.Character
import javax.inject.Inject

const val FIRST_PAGE = 1

class CharactersPagingSource @Inject constructor(
    private val repo: RMRepository
): PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: FIRST_PAGE
        return try {
            val characterList = repo.getCharacterList(page)
            LoadResult.Page(
                data = characterList,
                prevKey = null,
                nextKey = if (characterList.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}