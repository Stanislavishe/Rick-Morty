package com.example.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.presentation.fragments.CharacterList
import com.example.presentation.fragments.ItemCharacter
import com.example.presentation.fragments.ItemEpisodeFullscreen
import com.example.presentation.view_models.CharacterListViewModel
import com.example.presentation.view_models.EpisodeViewModel
import com.example.presentation.view_models.SingleCharacterViewModel
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
data class PersonScreen(val id: Int)

@Serializable
data class EpisodeScreen(val id: Int)


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    characterListViewModel: CharacterListViewModel = viewModel(),
    singleCharacterViewModel: SingleCharacterViewModel = viewModel(),
    episodeViewModel: EpisodeViewModel = viewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination = HomeScreen,
        modifier = modifier,
    ) {
        composable<HomeScreen> {
            CharacterList(
                characters = characterListViewModel.charactersList.collectAsLazyPagingItems().itemSnapshotList.items,
                modifier = modifier
            ) {
                navHostController.navigateSingleTopTo(route = PersonScreen(it))
            }
        }
        composable<PersonScreen> {
            val id = it.toRoute<PersonScreen>().id
            singleCharacterViewModel.loadPerson(id)
            val uiState = singleCharacterViewModel.uiState.collectAsState()
            ItemCharacter(uiState = uiState.value){ idEpisode ->
                navHostController.navigateSingleTopTo(route = EpisodeScreen(idEpisode))
            }
        }
        composable<EpisodeScreen> {
            val id = it.toRoute<EpisodeScreen>().id
            ItemEpisodeFullscreen(id = id, episodeViewModel = episodeViewModel) { idPerson ->
                navHostController.navigateSingleTopTo(route = PersonScreen(idPerson))
            }
        }
    }

}