package com.example.todoapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
import com.example.todoapplication.data.model.Status
import com.example.todoapplication.ui.activity.MainActivity
import com.example.todoapplication.utils.showMessage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_task.view.*

class TaskAdapter(private val dates: List<String>, private val context: Context) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.view_task, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = dates.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = dates[position]
        val itemView = holder.itemView
        itemView.apply {
            tv_date_task.text = date
            (context as MainActivity).initViewModel()
                .getTask(date)
                .observe(context as MainActivity, Observer {
                    it?.let { response ->
                        when (response.status) {
                            Status.StatusType.LOADING -> {
                                // loading
                            }
                            Status.StatusType.SUCCESS -> {
                                if (response.data != null && response.data.isNotEmpty()) {
                                    rv_detail_task.adapter =
                                        DetailTaskAdapter(response.data, context)
                                }
                            }
                            Status.StatusType.ERROR -> {
                                showMessage(response.message.toString(), "Load Date", context)
                            }
                        }
                    }
                })
        }
    }
}