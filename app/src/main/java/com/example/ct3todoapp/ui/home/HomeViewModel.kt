package com.example.ct3todoapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ct3todoapp.data.ToDo
import com.example.ct3todoapp.data.ToDoRepositoryImpl
import com.example.ct3todoapp.ui.add.AddViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(private val repositoryImpl: ToDoRepositoryImpl) : ViewModel() {

    var todos = repositoryImpl.getAll()

    suspend fun updateTask(toDo: ToDo) = viewModelScope.launch{
        repositoryImpl.update(toDo)

    }

    suspend fun deleteTask(toDo: ToDo) = viewModelScope.launch {
        repositoryImpl.delete(toDo)

    }

}

//class HomeViewModelFactory(private val repositoryImpl: ToDoRepositoryImpl) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            return HomeViewModel(repositoryImpl) as T
//        }
//        throw IllegalArgumentException("Unknown class for View Model")
//    }
//}


