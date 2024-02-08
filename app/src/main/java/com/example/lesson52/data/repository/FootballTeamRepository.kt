package com.example.lesson52.data.repository

import com.example.lesson52.data.model.FootballTeam

class FootballTeamRepository {

    private val footballTeams: MutableList<FootballTeam> = mutableListOf()

    fun getAllTeams() = footballTeams

    fun addTeam(footballTeam: FootballTeam) {
        if (!footballTeams.any { it.teamName == footballTeam.teamName }) {
            footballTeams.add(footballTeam)
        }
    }
}
