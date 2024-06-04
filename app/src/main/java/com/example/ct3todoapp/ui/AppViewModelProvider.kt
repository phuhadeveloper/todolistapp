package com.example.ct3todoapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ct3todoapp.ToDoApp
import com.example.ct3todoapp.ui.add.AddViewModel
import com.example.ct3todoapp.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(toDoApp().repository)
        }
        initializer {
            AddViewModel(toDoApp().repository)
        }
    }
}

fun CreationExtras.toDoApp(): ToDoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ToDoApp)