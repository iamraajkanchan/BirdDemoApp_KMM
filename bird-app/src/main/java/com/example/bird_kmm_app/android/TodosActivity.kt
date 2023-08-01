package com.example.bird_kmm_app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.example.bird_kmm_app.android.viewmodel.BirdViewModel
import com.example.bird_kmm_app.android.viewmodel.TodosViewModel
import com.example.bird_kmm_app.android.views.TodoCard

class TodosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val todosViewModel =
                    ViewModelProvider(this@TodosActivity)[TodosViewModel::class.java]
                DisplayAllTodos(viewModel = todosViewModel)
            }
        }
    }
}

@Composable
private fun DisplayAllTodos(viewModel: TodosViewModel) {
    TodoCard(viewModel = viewModel)
}