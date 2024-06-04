package com.example.ct3todoapp.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ct3todoapp.data.ToDo
import com.example.ct3todoapp.data.ToDoRepositoryImpl
import com.example.ct3todoapp.ui.home.HomeViewModel
import kotlinx.coroutines.launch

class AddViewModel(private val repositoryImpl: ToDoRepositoryImpl) : ViewModel() {

    var taskDetails by mutableStateOf("")

    suspend fun insert() = viewModelScope.launch {
        repositoryImpl.insert(ToDo(task = taskDetails, isComplete = false))
        taskDetails = ""
    }
}
