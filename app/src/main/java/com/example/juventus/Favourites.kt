import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.juventus.Player
import com.example.juventus.PlayerListItem

class Favourites : ViewModel() {


    var favouritePlayers by mutableStateOf(emptyList<Player>())
        private set

    fun addToFavourites(player: Player) {
        if (!favouritePlayers.contains(player)) {
            favouritePlayers = favouritePlayers + player
        }
    }


    fun removeFromFavourites(player: Player) {
        favouritePlayers = favouritePlayers - player
    }
}

@Composable
fun FavouritesScreen(favouritesViewModel: Favourites) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favourite Players",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(favouritesViewModel.favouritePlayers.chunked(2)) { rowPlayers ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowPlayers.forEach { player ->
                        PlayerListItem(
                            player = player,
                            onClick = {},
                            favouritesViewModel = favouritesViewModel,
                            onFavouriteClick = {
                                favouritesViewModel.removeFromFavourites(player)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FavouriteStarButton(
    isFavourite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Int = 24
) {
    IconButton(onClick = { onClick() }) {
        if (isFavourite) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Remove from Favourites",
                tint = Color.Yellow,
                modifier = modifier.size(iconSize.dp
                ))
        } else {
            Icon(
                imageVector = Icons.Outlined.StarBorder,
                contentDescription = "Add to Favourites",
                tint = Color.Gray,
                modifier = modifier.size(iconSize.dp)
            )
        }
    }
}




