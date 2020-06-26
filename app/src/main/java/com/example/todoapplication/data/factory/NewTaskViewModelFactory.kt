package com.example.todoapplication.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapplication.data.repository.NewTaskRepository
import com.example.todoapplication.data.viewmodel.NewTaskViewModel
import io.reactivex.disposables.CompositeDisposable

class NewTaskViewModelFactory (private val context: Context, private val newTaskRepository: NewTaskRepository, private val compositeDisposable: CompositeDisposable):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewTaskViewModel(context, newTaskRepository, compositeDisposable) as T
    }
}