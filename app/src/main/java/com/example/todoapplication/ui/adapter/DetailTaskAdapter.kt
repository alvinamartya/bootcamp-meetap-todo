package com.example.todoapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
import com.example.todoapplication.data.model.Task
import com.example.todoapplication.ui.activity.MainActivity
import kotlinx.android.synthetic.main.view_detail_task.view.*

class DetailTaskAdapter(private val tasks: List<Task>, private val context: Context) :
    RecyclerView.Adapter<DetailTaskAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.view_detail_task, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        val itemView = holder.itemView
        itemView.apply {
            cb_detail_task.isChecked = task.status
            tv_title_detail_task.text = task.title
            tv_time_detail_task.text = task.time

            if (!task.status) {
                cb_detail_task.setOnCheckedChangeListener { _, _ ->
                    task.status = true
                    (context as MainActivity).initViewModel().updateTask(task.id)
                    (context as MainActivity).loadDate()
                }
            } else {
                cb_detail_task.isEnabled = false
            }
        }
    }
}