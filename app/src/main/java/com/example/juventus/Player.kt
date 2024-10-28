package com.example.juventus

data class Player(
    val firstName: String,
    val surname: String,
    val position: String,
    val profileImageId: Int,
    val jerseyNumber: Int,
    val playerImage: Int,
    val profile: PlayerProfile

)

data class PlayerProfile(
    val dateOfBirth: String,
    val nationality: String,
    val bio: String,
    val appearances: Int,
    val saves: Int? = null,   // Only for goalkeepers
    val cleanSheets: Int? = null, // Only for goalkeepers
    val goals: Int? = null,
    val assists: Int? = null
)
