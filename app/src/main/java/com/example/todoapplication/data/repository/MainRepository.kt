package com.example.todoapplication.data.repository

import android.content.Context
import com.example.todoapplication.AppDatabase

class MainRepository private constructor(context: Context) {
    private var appDatabase: AppDatabase? = null

    init {
        appDatabase = AppDatabase.getInstance(context)
    }

    companion object {
        @JvmStatic
        private var instance: MainRepository? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: MainRepository(context).also { instance = it }
        }

        @JvmStatic
        fun destroyInstances() {
            instance = null
        }
    }

    fun getDate() = appDatabase!!.taskDao().getDate()
    fun getTask(date: String) = appDatabase!!.taskDao().getTask(date)
    fun update(status: Boolean, id: Int) = appDatabase!!.taskDao().update(status, id)
}