package com.example.bird_kmm_app.android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import com.example.bird_kmm_app.android.viewmodel.BirdViewModel
import com.example.bird_kmm_app.android.views.BirdCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val birdsViewModel = ViewModelProvider(this@MainActivity)[BirdViewModel::class.java]
                DisplayAllBirds(viewModel = birdsViewModel) { goToTodosActivity() }
            }
        }
    }

    private fun goToTodosActivity() {
        Intent(this@MainActivity, TodosActivity::class.java).apply {
            startActivity(this)
        }
    }
}

@Composable
private fun DisplayAllBirds(viewModel: BirdViewModel, call: () -> Unit) {
    BirdCard(viewModel, call)
}