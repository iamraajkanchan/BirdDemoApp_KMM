package com.example.bird_kmm_app.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bird_kmm_app.android.model.BirdResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BirdUiState(
    val birds: List<BirdResponse> = emptyList(),
    val selectedCategories: String? = null
) {
    val categories: Set<String?> = birds.map { it.category }.toSet()
    val selectedBirds = birds.filter { it.category == selectedCategories }
}

class BirdViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<BirdUiState> = MutableStateFlow(BirdUiState())
    val uiState: StateFlow<BirdUiState> = _uiState.asStateFlow()
    private val httpClient = HttpClient() { install(ContentNegotiation) { json() } }

    init {
        viewModelScope.launch { updateBirds() }
    }

    private suspend fun getBirds(): List<BirdResponse> {
        return httpClient.get("https://sebastianaigner.github.io/demo-image-api/pictures.json")
            .body()
    }

    private fun updateBirds() {
        viewModelScope.launch {
            _uiState.update { it.copy(birds = getBirds(), selectedCategories = "PIGEON") }
        }
    }

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategories = category) }
    }

    override fun onCleared() {
        httpClient.close()
    }
}