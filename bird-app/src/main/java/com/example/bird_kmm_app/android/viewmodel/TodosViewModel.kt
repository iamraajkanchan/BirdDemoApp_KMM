package com.example.bird_kmm_app.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bird_kmm_app.android.model.TodosResponse
import com.example.bird_kmm_app.android.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TodosUiState(
    val todos: List<TodosResponse> = emptyList(),
    val todosType: Boolean? = null
) {
    val selectedTodos = if (todosType != null) todos.filter { it.completed == todosType } else todos
}

class TodosViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<TodosUiState> = MutableStateFlow(TodosUiState())
    val uiState: StateFlow<TodosUiState> get() = _uiState
    private val repository: AppRepository = AppRepository.getInstance()

    init {
        updateTodos()
    }

    private fun updateTodos() {
        viewModelScope.launch {
            _uiState.update { it.copy(todos = repository.getTodos()) }
        }
    }

    fun setTodosType(type: Boolean?) {
        _uiState.update { it.copy(todosType = type) }
    }
}