package com.example.todoapplication.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todoapplication.R
import com.example.todoapplication.data.viewmodel.NewTaskViewModel
import com.example.todoapplication.ui.dialog.ProgressDialog
import com.example.todoapplication.utils.InjectorViewModel
import com.example.todoapplication.utils.showMessage
import com.vivekkaushik.datepicker.OnDateSelectedListener
import kotlinx.android.synthetic.main.activity_new_task.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        var date = LocalDateTime.now()
        datePickerTimeline.setInitialDate(date.year, date.monthValue, date.dayOfMonth)
        datePickerTimeline.setOnDateSelectedListener(object : OnDateSelectedListener {
            override fun onDateSelected(year: Int, month: Int, day: Int, dayOfWeek: Int) {
                date = LocalDateTime.of(year, month, day, date.hour, date.minute)
            }

            override fun onDisabledDateSelected(
                year: Int,
                month: Int,
                day: Int,
                dayOfWeek: Int,
                isDisabled: Boolean
            ) {

            }
        })

        btn_add_new_task.setOnClickListener {
            when {
                edt_task_name_new_task.text.toString().isEmpty() -> showMessage(
                    "Task name must be filled",
                    "New Task",
                    this@NewTaskActivity
                )
                else -> {
                    date = LocalDateTime.of(
                        date.year,
                        date.month,
                        date.dayOfMonth,
                        timePicker.hour,
                        timePicker.minute
                    )

                    val datePattern = DateTimeFormatter.ofPattern("dd MMMM yyyy")
                    val timePattern = DateTimeFormatter.ofPattern("HH:mm")

                    val dialog = ProgressDialog("New Task")
                    dialog.showDialog(supportFragmentManager)

                    initViewModel().addTask(
                        edt_task_name_new_task.text.toString(),
                        date.format(datePattern),
                        date.format(timePattern),
                        dialog
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        initViewModel().destroy()
    }

    private fun initViewModel(): NewTaskViewModel {
        val factory = InjectorViewModel.providerNewTaskViewModel(this@NewTaskActivity)
        return ViewModelProvider(this, factory).get(NewTaskViewModel::class.java)
    }
}