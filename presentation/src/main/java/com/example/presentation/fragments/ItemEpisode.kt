package com.example.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.CharacterUIState
import com.example.presentation.view_models.EpisodeViewModel

@Composable
fun ItemEpisodeFullscreen(
    modifier: Modifier = Modifier,
    id: Int,
    episodeViewModel: EpisodeViewModel = viewModel(),
    onClick: (Int) -> Unit
) {
    episodeViewModel.loadEpisode(id)
    val uiState = episodeViewModel.uiState.collectAsState().value


    if (uiState is CharacterUIState.SuccessLoadEpisode) {
        Column {
            Text(
                text = uiState.episode.name,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.onBackground,
                                MaterialTheme.colorScheme.surfaceVariant
                            )
                        )
                    )
            )
            Text(
                text = "Дата публиукации",
                modifier = Modifier.padding(start = 16.dp, top = 10.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = uiState.episode.airDate, modifier = Modifier.padding(start = 16.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(1)) {
                items(uiState.persons, key = { it.id }) {
                    ItemList(
                        info = it,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                    ) { id ->
                        onClick(id)
                    }
                }
            }
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//private fun ItemEpisodePrew() {
//    Column(modifier = Modifier.fillMaxSize()) {
//        Text(
//            text = "character.name",
//            modifier = Modifier.padding(16.dp),
//            style = MaterialTheme.typography.headlineSmall,
//            fontWeight = FontWeight.ExtraBold,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis
//        )
//        Spacer(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(1.dp)
//                .background(
//                    Brush.linearGradient(
//                        listOf(
//                            MaterialTheme.colorScheme.onBackground,
//                            MaterialTheme.colorScheme.surfaceVariant
//                        )
//                    )
//                )
//        )
//        Text(
//            text = "Дата публиукации",
//            modifier = Modifier.padding(start = 16.dp, top = 10.dp),
//            style = MaterialTheme.typography.titleMedium
//        )
//        Text(text = "sdkvs;jvn", modifier = Modifier.padding(start = 16.dp))
//        LazyVerticalGrid(columns = GridCells.Fixed(1)) {
//            items()
//        }
//    }
//}