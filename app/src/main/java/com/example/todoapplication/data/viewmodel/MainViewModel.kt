package com.example.todoapplication.data.viewmodel

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapplication.data.model.Status
import com.example.todoapplication.data.model.Task
import com.example.todoapplication.data.repository.MainRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val context: Context,
    private val mainRepository: MainRepository,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {
    fun getDate(): LiveData<Status<List<String>>> {
        val result = MutableLiveData<Status<List<String>>>()

        result.postValue(Status.loading())
        compositeDisposable.add(
            mainRepository.getDate()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        result.postValue(Status.error(it.message.toString(), null))
                    },
                    onSuccess = {
                        it?.let { response ->
                            result.postValue(Status.success(response))
                        }
                    }
                )
        )

        return result
    }

    fun getTask(date: String): LiveData<Status<List<Task>>> {
        val result = MutableLiveData<Status<List<Task>>>()

        result.postValue(Status.loading())
        compositeDisposable.add(
            mainRepository.getTask(date)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        result.postValue(Status.error(it.message.toString(), null))
                    },
                    onSuccess = {
                        it?.let { response ->
                            result.postValue(Status.success(response))
                        }
                    }
                )
        )

        return result
    }

    fun updateTask(id: Int) {
        compositeDisposable.add(
            Observable
                .fromCallable { mainRepository.update(true, id) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }
}