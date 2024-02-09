package com.example.lesson52.data.repository

import com.example.lesson52.App
import com.example.lesson52.data.local.room.entities.FootballTeam

class FootballTeamRepository {
    private val footballTeamsDao = App.db.footballTeamsDao()

    fun getAllTeams(): List<FootballTeam> {
        return footballTeamsDao.getAll()
    }

    fun addTeam(footballTeam: FootballTeam) {
        val teams = getAllTeams()
        val existingTeam = teams.find { it.teamName == footballTeam.teamName }
        if (existingTeam == null) {
            footballTeamsDao.addTeam(footballTeam)
        }
    }

    fun deleteTeam(footballTeam: FootballTeam) {
        footballTeamsDao.deleteTeam(footballTeam)
        footballTeamsDao.updateTeam(footballTeam)
    }


}
