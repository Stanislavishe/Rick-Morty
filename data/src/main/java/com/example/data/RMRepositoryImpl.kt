package com.example.data

import com.example.domain.LoadCharacterResult
import com.example.domain.RMRepository
import com.example.domain.models.Character
import com.example.domain.models.Episode
import javax.inject.Inject

class RMRepositoryImpl @Inject constructor(
    private val api: RMApi
) : RMRepository {
    private val  regex = Regex("[0-9]")
    override suspend fun getCharacterList(page: Int): List<Character> {
        return try {
            api.personsList(page).results
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getSinglePerson(id: Int): LoadCharacterResult {
        return try {
            val character = api.singlePerson(id.toString())
            val episodes = mutableListOf<Episode>()

            character.episode?.forEach {
                var episodeId = ""
                it.toCharArray().forEach { char ->
                    if(regex.matches(char.toString())) episodeId += char
                }
                val episode = api.singleEpisode(episodeId)
                episodes.add(episode)
            }

            LoadCharacterResult.SuccessSingleCharacter(character, episodes)
        } catch (e: Exception) {
            LoadCharacterResult.Error(e.message ?: "")
        }
    }

    override suspend fun getSingleEpisode(id: String): LoadCharacterResult {
        return try {
            val episode = api.singleEpisode(id)
            val characters = mutableListOf<Character>()

            episode.characters.forEach {
                var personId = ""
                it.toCharArray().forEach { char ->
                    if(regex.matches(char.toString())) personId += char
                }
                val person = api.singlePerson(personId)
                characters.add(person)
            }

            LoadCharacterResult.SuccessSingleEpisode(episode, characters)
        } catch (e: Exception) {
            LoadCharacterResult.Error(e.message ?: "")
        }
    }

}