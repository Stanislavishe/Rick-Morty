package com.example.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
object PersonScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: CharacterListViewModel = viewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination = HomeScreen,
        modifier = modifier
    ) {
        composable<HomeScreen> {
            CharacterList(
                characters = viewModel.charactersList.collectAsLazyPagingItems().itemSnapshotList.items,
                modifier = modifier){
                navHostController.navigate(route = PersonScreen)
            }
        }
        composable<PersonScreen> {

        }
    }

}