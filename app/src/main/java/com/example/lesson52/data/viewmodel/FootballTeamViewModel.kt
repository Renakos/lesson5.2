package com.example.lesson52.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson52.data.local.room.entities.FootballTeam
import com.example.lesson52.data.repository.FootballTeamRepository
import com.example.lesson52.utils.UiState
import kotlinx.coroutines.launch

class FootballTeamViewModel : ViewModel() {
    private val repository = FootballTeamRepository()


    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState


    private val _data = MutableLiveData<List<FootballTeam>>()
    val data: LiveData<List<FootballTeam>> = _data

    val allTeams = repository.getAllTeams()
    fun addData(footballTeam: FootballTeam) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                repository.addTeam(footballTeam)
                val updatedTeams = repository.getAllTeams()
                _uiState.value = UiState.Success(updatedTeams)
                _data.value = updatedTeams
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }

    fun deleteTeam(footballTeam: FootballTeam) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                repository.deleteTeam(footballTeam)
                val updatedTeams = repository.getAllTeams()
                _data.value = updatedTeams
                _uiState.value = UiState.Success(updatedTeams)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }


}
