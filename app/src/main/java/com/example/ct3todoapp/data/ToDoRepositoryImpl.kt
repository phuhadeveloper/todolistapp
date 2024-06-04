package com.example.ct3todoapp.data

import kotlinx.coroutines.flow.Flow

class ToDoRepositoryImpl(
    private val dao: ToDoDao
) : ToDoRepository {
    override fun getAll(): Flow<List<ToDo>> {
        return dao.getAll()
    }

    override suspend fun delete(todo: ToDo) {
        dao.delete(todo)
    }

    override suspend fun insert(todo: ToDo) {
        dao.insert(todo)
    }

    override suspend fun update(todo: ToDo) {
        dao.update(todo)
    }
}