package com.example.todoapplication.utils

import android.content.Context
import com.example.todoapplication.data.factory.MainViewModelFactory
import com.example.todoapplication.data.factory.NewTaskViewModelFactory
import com.example.todoapplication.data.repository.MainRepository
import com.example.todoapplication.data.repository.NewTaskRepository
import com.example.todoapplication.data.viewmodel.NewTaskViewModel
import io.reactivex.disposables.CompositeDisposable

object InjectorViewModel {
    fun providerNewTaskViewModel(context: Context): NewTaskViewModelFactory {
        val compositeDisposable = CompositeDisposable()
        val newTaskRepository = NewTaskRepository.getInstance(context)
        return NewTaskViewModelFactory(context, newTaskRepository, compositeDisposable)
    }

    fun providerMainViewModel(context: Context): MainViewModelFactory {
        val compositeDisposable = CompositeDisposable()
        val mainRepository = MainRepository.getInstance(context)
        return MainViewModelFactory(context, mainRepository, compositeDisposable)
    }
}