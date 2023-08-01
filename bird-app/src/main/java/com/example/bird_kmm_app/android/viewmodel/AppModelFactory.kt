package com.example.bird_kmm_app.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class AppModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BirdViewModel::class.java)) {
            BirdViewModel() as T
        } else if (modelClass.isAssignableFrom(TodosViewModel::class.java)) {
            TodosViewModel() as T
        } else {
            throw IllegalArgumentException("Local and anonymous classes can not be ViewModels")
        }
    }
}