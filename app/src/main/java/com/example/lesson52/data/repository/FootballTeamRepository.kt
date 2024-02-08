package com.example.lesson52.data.repository

import com.example.lesson52.data.local.room.dao.FootballTeamsDao
import com.example.lesson52.data.local.room.entities.FootballTeam

class FootballTeamRepository {

    private val footballTeams: MutableList<FootballTeam> = mutableListOf()
    private lateinit var footballTeamsDao: FootballTeamsDao

    fun setFootballTeamsDao(dao: FootballTeamsDao) {
        footballTeamsDao = dao
    }

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

    // Дополнительные методы для обновления, удаления и т.д. можно добавить здесь
}
