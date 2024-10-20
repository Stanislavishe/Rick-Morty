package com.example.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCbrt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.models.Character
import com.example.domain.models.Origin
import com.example.presentation.theme.RickAndMortyTheme

@Composable
fun ItemCharacter(
    modifier: Modifier = Modifier,
    character: Character,
//    viewModel: SingleCharacterViewModel = viewModel()
) {
    Column(
        modifier = modifier
    ) {
        GlideImagePreview(
            data = character.image, modifier = Modifier
                .fillMaxWidth()
                .height(170.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = character.name,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineMedium,
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
            text = "Live status",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(top = 10.dp, start = 16.dp)
        )
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            val imageId =
                if (character.status == "Alive") R.drawable.green_point else R.drawable.red_point
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 4.dp, top = 4.dp)
                    .size(10.dp)
            )
            Text(text = character.status.toString(), style = MaterialTheme.typography.bodyLarge)
        }
        ItemInfo(
            modifier = Modifier.padding(start = 16.dp, top = 10.dp),
            title = "Species and gender:", content = character.gender.toString()
        )
        ItemInfo(
            modifier = Modifier.padding(start = 16.dp, top = 10.dp),
            title = "Last know location:", content = character.location.name
        )
        ItemInfo(
            modifier = Modifier.padding(start = 16.dp, top = 10.dp),
            title = "First seen in:", content = character.origin.name
        )
        Text(
            text = "Episodes:",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 20.dp)
        )
//        val episodes = viewModel.loadEpisode.collectAsState()
//        LazyColumn(
//            userScrollEnabled = false,
//            modifier = Modifier.padding(start = 16.dp)
//        ) {
//
//        }
    }
}

@Composable
fun ItemInfo(modifier: Modifier = Modifier, content: String, title: String) {
    Column(
        modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline
        )
        Text(text = content, style = MaterialTheme.typography.bodyLarge)
    }

}

//@Composable
//fun ItemEpisode(modifier: Modifier = Modifier) {
//
//}

@Preview(showBackground = true)
@Composable
private fun CharacterPreview() {
    RickAndMortyTheme {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val origin = Origin(
                name = "name",
                url = "https://otvet.imgsmail.ru/download/101879595_5086b0b93541f4017e5aec267895a4bd_800.jpg"
            )
            val testCharacter = Character(
                id = 1,
                name = "James Bond",
                status = "Harosh",
                species = "Chydo",
                gender = "gender",
                origin = origin,
                episode = listOf(""),
                image = null,
                location = origin
            )
            ItemCharacter(character = testCharacter)
        }
    }
}

