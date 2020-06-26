package com.example.todoapplication.data.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapplication.data.repository.MainRepository
import com.example.todoapplication.data.repository.NewTaskRepository
import com.example.todoapplication.data.viewmodel.MainViewModel
import com.example.todoapplication.data.viewmodel.NewTaskViewModel
import io.reactivex.disposables.CompositeDisposable

class MainViewModelFactory (private val context: Context, private val mainRepository: MainRepository, private val compositeDisposable: CompositeDisposable):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(context, mainRepository, compositeDisposable) as T
    }
}