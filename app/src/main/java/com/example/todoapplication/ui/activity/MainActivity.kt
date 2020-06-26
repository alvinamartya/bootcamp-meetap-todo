package com.example.todoapplication.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todoapplication.R
import com.example.todoapplication.data.model.Status
import com.example.todoapplication.data.viewmodel.MainViewModel
import com.example.todoapplication.data.viewmodel.NewTaskViewModel
import com.example.todoapplication.ui.adapter.TaskAdapter
import com.example.todoapplication.utils.InjectorViewModel
import com.example.todoapplication.utils.showMessage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            startActivity(
                Intent(this, NewTaskActivity::class.java)
            )
        }

        loadDate()
    }

    override fun onResume() {
        super.onResume()
        loadDate()
    }

    fun loadDate() {
        initViewModel()
            .getDate()
            .observe(this@MainActivity, Observer {
                it?.let { response ->
                    when (response.status) {
                        Status.StatusType.LOADING -> {
                            progressbar.visibility = View.VISIBLE
                            rv_task.visibility = View.GONE
                            tv_error_task.visibility = View.GONE
                        }
                        Status.StatusType.SUCCESS -> {
                            if (response.data != null && response.data.isNotEmpty()) {
                                rv_task.adapter = TaskAdapter(response.data, this@MainActivity)

                                progressbar.visibility = View.GONE
                                rv_task.visibility = View.VISIBLE
                                tv_error_task.visibility = View.GONE
                            } else {
                                progressbar.visibility = View.GONE
                                rv_task.visibility = View.GONE
                                tv_error_task.visibility = View.VISIBLE

                                tv_error_task.text =
                                    applicationContext.resources.getString(R.string.data_is_empty)
                            }
                        }
                        Status.StatusType.ERROR -> {
                            progressbar.visibility = View.GONE
                            rv_task.visibility = View.GONE
                            tv_error_task.visibility = View.VISIBLE

                            tv_error_task.text = response.message.toString()
                        }
                    }
                }
            })
    }

    fun initViewModel(): MainViewModel {
        val factory = InjectorViewModel.providerMainViewModel(this@MainActivity)
        return ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }
}