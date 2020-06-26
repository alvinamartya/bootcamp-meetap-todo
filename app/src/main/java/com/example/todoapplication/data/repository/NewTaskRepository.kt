package com.example.todoapplication.data.repository

import android.content.Context
import com.example.todoapplication.AppDatabase
import com.example.todoapplication.data.model.Task

class NewTaskRepository private constructor(context: Context) {
    private var appDatabase: AppDatabase? = null

    init {
        appDatabase = AppDatabase.getInstance(context)
    }

    companion object {
        @JvmStatic
        private var instance: NewTaskRepository? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: NewTaskRepository(context).also { instance = it }
        }

        @JvmStatic
        fun destroyInstances() {
            instance = null
        }
    }

    fun insertTask(task: Task) = appDatabase?.taskDao()?.insertTask(task)
}