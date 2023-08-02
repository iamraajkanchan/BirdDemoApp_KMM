package com.example.bird_kmm_app.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bird_kmm_app.android.model.BirdResponse
import com.example.bird_kmm_app.android.repository.AppRepository
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
    private val repository: AppRepository = AppRepository.getInstance()
    val uiState: StateFlow<BirdUiState> = _uiState.asStateFlow()

    init {
        updateBirds()
    }

    private fun updateBirds() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    birds = repository.getBirds(),
                    selectedCategories = "PIGEON"
                )
            }
        }
    }

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategories = category) }
    }

    override fun onCleared() {
        repository.shutHttpClient()
    }
}