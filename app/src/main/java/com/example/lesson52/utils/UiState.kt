package com.example.lesson52.utils

import com.example.lesson52.data.model.FootballTeam

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<FootballTeam>) : UiState()
    data class Error(val message: String) : UiState()
}
