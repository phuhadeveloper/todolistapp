package com.example.ct3todoapp.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun insert(todo: ToDo)

    suspend fun delete(todo: ToDo)

    fun getAll(): Flow<List<ToDo>>

    suspend fun update(todo: ToDo)

}