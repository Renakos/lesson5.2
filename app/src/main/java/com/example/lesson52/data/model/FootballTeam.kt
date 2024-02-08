package com.example.lesson52.data.model

data class FootballTeam(
    val teamName: String,
    val amountOfPlayers: Int,
    val players: Set<String>
)

