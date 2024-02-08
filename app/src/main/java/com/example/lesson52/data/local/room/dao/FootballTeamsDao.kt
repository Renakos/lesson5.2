package com.example.lesson52.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lesson52.data.local.room.entities.FootballTeam

@Dao
interface FootballTeamsDao {
    @Query("SELECT * FROM football_team_table")
    fun getAll(): List<FootballTeam>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTeam(team: FootballTeam)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTeams(teams: List<FootballTeam>)

    @Update
    fun updateTeam(team: FootballTeam)

    @Delete
    fun deleteTeam(team: FootballTeam)

    @Query("DELETE FROM football_team_table")
    fun clear()
}