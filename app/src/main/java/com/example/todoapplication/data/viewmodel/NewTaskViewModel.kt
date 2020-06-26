package com.example.todoapplication.data.viewmodel

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.todoapplication.data.model.Task
import com.example.todoapplication.data.repository.NewTaskRepository
import com.example.todoapplication.ui.dialog.ProgressDialog
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewTaskViewModel(
    private val context: Context,
    private val newTaskRepository: NewTaskRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {
    fun addTask(title: String, date: String, time: String, dialog: ProgressDialog) {
        compositeDisposable.add(
            Observable
                .fromCallable { newTaskRepository.insertTask(Task(0, title, date, time, false)) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    AlertDialog
                        .Builder(context)
                        .setTitle("New Task")
                        .setMessage("Add new task has been success")
                        .setPositiveButton("OK") { _, _ ->
                            dialog.dismiss()
                            (context as Activity).finish()
                        }
                        .show()
                })
    }

    fun destroy() {
        NewTaskRepository.destroyInstances()
    }
}