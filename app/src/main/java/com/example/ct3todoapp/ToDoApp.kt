package com.example.ct3todoapp

import android.app.Application
import com.example.ct3todoapp.data.ToDoDatabase
import com.example.ct3todoapp.data.ToDoRepositoryImpl

class ToDoApp : Application() {
    private val database by lazy { ToDoDatabase.getDatabase(this) }

    val repository by lazy { ToDoRepositoryImpl(database.itemDao()) }
}