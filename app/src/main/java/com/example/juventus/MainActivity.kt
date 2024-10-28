package com.example.juventus

import FavouriteStarButton
import Favourites
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.juventus.ui.theme.JuventusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JuventusTheme {
            }
            val navController = rememberNavController()
            val favouritesViewModel: Favourites = viewModel()

            val players = mapOf(
                "Goalkeeper" to listOf(
                    Player("Carlo", "Pinsoglio", "Goalkeeper", R.drawable.pinsoglio, 23, R.drawable.pinsogliofull,
                        profile = PlayerProfile(
                            "16 March 1990",
                            "Italian",
                            "Veteran third-choice goalkeeper, valued for his experience and leadership in the dressing room.",
                            appearances = 0, saves = 0, cleanSheets = 0
                        ))
                ),
                "Defenders" to listOf(
                    Player("Gleison", "Bremer", "Defender", R.drawable.bremer, 3, R.drawable.bremerfull,
                        profile = PlayerProfile(
                            "18 March 1997",
                            "Brazilian",
                            "Solid, athletic center-back known for his strong tackles and aerial ability.",
                            appearances = 8, goals = 0, assists = 0
                        )),
                    Player("Luiz da Silva", "Danilo", "Defender", R.drawable.danilo, 6, R.drawable.danilofull,
                        profile = PlayerProfile(
                            "15 July 1991",
                            "Brazilian",
                            "A versatile and reliable defender, Danilo is known for his leadership and tactical awareness.",
                            appearances = 4, goals = 0, assists = 0)),
                    Player("Pierre", "Kalulu", "Defender", R.drawable.kalulu, 15, R.drawable.kalulufull,
                        profile = PlayerProfile(
                            "05 June 2000",
                            "French",
                            "Quick and agile, Kalulu brings energy and dynamism to the defense.",
                            appearances = 7, goals = 0, assists = 0)),
                    Player("Yannick", "Rouhi", "Defender", R.drawable.rouhi, 40, R.drawable.rouhifull,
                        profile = PlayerProfile(
                            "07 January 2004",
                            "Sweden",
                            "A promising young defender rising through the ranks of Juventus' youth system.",
                            appearances = 2, goals = 0, assists = 0))
                ),
                "Midfielders" to listOf(
                    Player("Teun", "Koopmeiners", "Midfielder", R.drawable.koopmeiners, 8, R.drawable.koopmeinersfull,
                        profile = PlayerProfile(
                            "28 February 1998",
                            "Dutch",
                            "A deep-lying playmaker with a great vision for passing and dictating tempo.",
                            appearances = 7, goals = 0, assists = 1)),
                    Player("Manuel", "Locatelli", "Midfielder", R.drawable.locatelli, 5, R.drawable.locatellifull,
                        profile = PlayerProfile(
                            "08 January 1998",
                            "Italian",
                            "A central midfielder with excellent ball control and defensive abilities.",
                            appearances = 7, goals = 0, assists = 0)),
                    Player("Douglas", "Luiz", "Midfielder", R.drawable.luiz, 26, R.drawable.luizfull,
                        profile = PlayerProfile(
                            "09 May 1998",
                            "Brazilian",
                            "Dynamic box-to-box midfielder, known for his work rate and passing range.",
                            appearances = 8, goals = 0, assists = 0))
                ),
                "Forwards" to listOf(
                    Player("Arkadiusz", "Milik", "Forward", R.drawable.milik, 14, R.drawable.milikfull,
                        profile = PlayerProfile(
                            "28 February 1994",
                            "Polish",
                            "Strong and clinical forward, with a great ability to hold the ball up and finish chances.",
                            appearances = 0, goals = 0, assists = 0)),
                    Player("Nicolás Iván", "González", "Forward", R.drawable.gonzalez, 11, R.drawable.gonzalezfull,
                        profile = PlayerProfile(
                            "06 April 1998",
                            "Argentine",
                            "A pacey winger with excellent dribbling skills and a knack for creating chances.",
                            appearances = 6, goals = 1, assists = 1)),
                    Player("Dusan", "Vlahovic", "Forward", R.drawable.vlahovic, 9, R.drawable.vlahovicfull,
                        profile = PlayerProfile(
                            "28 January 2000",
                            "Serbian",
                            "Prolific goal scorer with incredible physical presence and finishing ability.",
                            appearances = 9, goals = 7, assists = 1))
                )
            )

            Scaffold(
                bottomBar = {
                    BottomNavigationBar(navController = navController)
                }
            ) { paddingValues ->
                NavigationHost(navController = navController, favouritesViewModel = favouritesViewModel, players = players, paddingValues = paddingValues)
            }
        }
    }
}


@Composable
fun PlayerListItem(player: Player,
                   onClick: () -> Unit,
                   favouritesViewModel: Favourites,
                   onFavouriteClick: () -> Unit) {
    val isFavourite by remember {
        derivedStateOf { favouritesViewModel.favouritePlayers.contains(player) }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)

    ) {
        Box {
            FavouriteStarButton(
                isFavourite = isFavourite,
                onClick = onFavouriteClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            )
            Image(
                painter = painterResource(id = player.profileImageId),
                contentDescription = "${player.surname}'s Image",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Fit
            )


        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = player.jerseyNumber.toString(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 16.dp)
            )


            Column {
                Text(
                    text = player.firstName,
                    fontSize = 18.sp
                )

                // Surname (larger, bold)
                Text(
                    text = player.surname,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun TeamHomeScreen(
    players: Map<String, List<Player>>,
    favouritesViewModel: Favourites
) {
    var selectedPlayer by remember { mutableStateOf<Player?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.juventus),
                contentDescription = "Juventus FC Logo",
                modifier = Modifier
                    .size(150.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Juventus FC",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = "2024 Starting 11",
                fontSize = 20.sp,
                modifier = Modifier.padding(4.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                players.forEach { (position, playersInPosition) ->
                    item {
                        Text(
                            text = position,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }

                    items(playersInPosition.chunked(2)) { rowPlayers ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            rowPlayers.forEach { player ->
                                PlayerListItem(
                                    player = player,
                                    onClick = { selectedPlayer = player },
                                    favouritesViewModel = favouritesViewModel,
                                    onFavouriteClick = {
                                        if (favouritesViewModel.favouritePlayers.contains(player)) {
                                            favouritesViewModel.removeFromFavourites(player)
                                        } else {
                                            favouritesViewModel.addToFavourites(player)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }

            selectedPlayer?.let { player ->
                PlayerDetails(player = player,
                    onDismiss = { selectedPlayer = null },
                    favouritesViewModel = favouritesViewModel
                    )
            }
        }
    }
}





@Composable
fun PlayerDetails(player: Player,
                  onDismiss: () -> Unit,
                  favouritesViewModel: Favourites) {
    val isFavourite by remember {
        derivedStateOf { favouritesViewModel.favouritePlayers.contains(player) }
    }
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.height(100.dp))

                Box(
                    modifier = Modifier
                        .width(350.dp)
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .padding(2.dp)
                ) {
                    Image(
                        painter = painterResource(id = player.playerImage),
                        contentDescription = "${player.firstName} ${player.surname}'s Image",
                        modifier = Modifier.fillMaxHeight(),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {}


                        Column(
                            modifier = Modifier
                                .background(Color.Gray.copy(alpha = 0.5f))
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "${player.firstName} ${player.surname}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Date of Birth: ${player.profile.dateOfBirth}",
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Text(
                                text = "Nationality: ${player.profile.nationality}",
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Text(
                                text = "Additional Info: ${player.profile.bio}",
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))


                        Column(
                            modifier = Modifier
                                .background(Color.Gray.copy(alpha = 0.5f))
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Statistics",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Appearances: ${player.profile.appearances}",
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Text(
                                text = if (player.position == "Goalkeeper"){
                                  "saves: ${player.profile.saves}"
                                }else{
                                    "goals: ${player.profile.goals}"
                                },

                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Text(
                                text = if (player.position == "Goalkeeper"){
                                    "Clean Sheets: ${player.profile.cleanSheets}"
                                }else{
                                    "Assists: ${player.profile.assists}"
                                },
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            FavouriteStarButton(
                                isFavourite = isFavourite,
                                onClick = {
                                    if (isFavourite) {
                                        favouritesViewModel.removeFromFavourites(player)
                                    } else {
                                        favouritesViewModel.addToFavourites(player)
                                    }
                                },
                                modifier = Modifier.size(100.dp),
                                iconSize = 48
                            )


                            Text(
                                text = "Close",
                                fontSize = 18.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .background(Color.Red, shape = RoundedCornerShape(CornerSize(8.dp)))
                                    .clickable { onDismiss() }
                                    .padding(vertical = 8.dp, horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun JuventusPreview() {
    val players = mapOf(
        "Goalkeeper" to listOf(
            Player("Carlo", "Pinsoglio", "Goalkeeper", R.drawable.pinsoglio, 23,R.drawable.pinsogliofull,
                profile = PlayerProfile(
                    "16 March 1990",
                    "Italian",
                    "to be added",
                    appearances = 0, saves = 0, cleanSheets = 0
                ))
        ),
        "Defender" to listOf(
            Player("Gleison", "Bremer", "Defender", R.drawable.bremer, 3,R.drawable.bremerfull,
                profile = PlayerProfile(
                    "18 March 1997",
                    "Brazilian",
                    "to be added",
                    appearances = 8, goals = 0, assists = 0)),
            Player("Luiz da Silva", "Danilo", "Defender", R.drawable.danilo, 6,R.drawable.danilofull,
                profile = PlayerProfile(
                    "15 July 1991",
                    "Brazilian",
                    "to be added",
                    appearances = 4, goals = 0, assists = 0)),
            Player("Pierre", "Kalulu", "Defender", R.drawable.kalulu, 15,R.drawable.kalulufull,
                profile = PlayerProfile(
                    "05 June 2000",
                    "French",
                    "to be added",
                    appearances = 7, goals = 0, assists = 0)),
            Player("Yannick", "Rouhi", "Defender", R.drawable.rouhi, 40,R.drawable.rouhifull,
                profile = PlayerProfile(
                    "07 January 2004",
                    "Sweden",
                    "to be added",
                    appearances = 2, goals = 0, assists = 0))
        ),
        "Midfielder" to listOf(
            Player("Teun", "Koopmeiners", "Midfielder", R.drawable.koopmeiners, 8,R.drawable.koopmeinersfull,
                profile = PlayerProfile(
                    "28 February 1998",
                    "Dutch",
                    "to be added",
                    appearances = 7, goals = 0, assists = 1)),
            Player("Manuel", "Locatelli", "Midfielder", R.drawable.locatelli, 5,R.drawable.locatellifull,
                profile = PlayerProfile(
                    "08 January 1998",
                    "Italian",
                    "to be added",
                    appearances = 7, goals = 0, assists = 0)),
            Player("Douglas", "Luiz", "Midfielder", R.drawable.luiz, 26,R.drawable.luizfull,
                profile = PlayerProfile(
                    "09 May 1998",
                    "Brazilian",
                    "to be added",
                    appearances = 8, goals = 0, assists = 0))
        ),
        "Forward" to listOf(
            Player("Arkadiusz", "Milik", "Forward", R.drawable.milik, 14,R.drawable.milikfull,
                profile = PlayerProfile(
                    "28 February 1994",
                    "Polish",
                    "to be added",
                    appearances = 0, goals = 0, assists = 0)),
            Player("Nicolás Iván", "González", "Forward", R.drawable.gonzalez, 11,R.drawable.gonzalezfull,
                profile = PlayerProfile(
                    "06 April 1998",
                    "Argentine",
                    "to be added",
                    appearances = 6, goals = 1, assists = 1)),
            Player("Dusan", "Vlahovic", "Forward", R.drawable.vlahovic, 9,R.drawable.vlahovicfull,
                profile = PlayerProfile(
                    "28 January 2000",
                    "Serbian",
                    "to be added",
                    appearances = 9, goals = 7, assists = 1))
        )
    )
    val mockFavouritesViewModel = Favourites()

    TeamHomeScreen(players = players, favouritesViewModel = mockFavouritesViewModel )
}
