package com.example.lesson52.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "football_team_table")
data class FootballTeam(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo("name")
    val teamName: String,
    @ColumnInfo("amount")
    val amountOfPlayers: Int,
    @ColumnInfo("team_players")
    val players: Set<String>
)

