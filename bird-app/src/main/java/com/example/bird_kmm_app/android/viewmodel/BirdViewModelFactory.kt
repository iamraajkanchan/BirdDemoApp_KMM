package com.example.bird_kmm_app.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class BirdViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BirdViewModel::class.java)) {
            return BirdViewModel() as T
        } else {
            throw IllegalArgumentException("Local and anonymous classes can not be ViewModels")
        }
    }
}