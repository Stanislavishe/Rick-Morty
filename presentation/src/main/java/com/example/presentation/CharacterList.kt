package com.example.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.ItemSnapshotList
import androidx.paging.compose.LazyPagingItems
import com.example.domain.models.Character

@Composable
fun CharacterList(
    modifier: Modifier = Modifier,
    characters: List<Character>,
    onNavigate: () -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = characters, key = {it.id}) {
            ItemList(info = it, modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth())
        }
    }
}

@Composable
fun ItemList(
    modifier: Modifier = Modifier,
    info: Character
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Row {
            GlideImagePreview(
                data = info.image,
                contentDescription = "Hero Preview",
                modifier = Modifier.size(120.dp)
            )
            Column(
                modifier = Modifier.padding(start = 6.dp)
            ) {
                Text(
                    text = info.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val imageId =
                        if (info.status == "Alive") R.drawable.green_point else R.drawable.red_point
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .size(15.dp)

                    )
                    Text(
                        text = "${info.status} - ${info.species}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Text(
                    text = "Last known location:",
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(text = info.location?.name!!, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}
