package com.example.todoapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.todoapplication.data.model.Task
import io.reactivex.Single

@Dao
interface TaskDao {
    @Query("SELECT DISTINCT date FROM Task ORDER BY date ASC")
    fun getDate(): Single<List<String>>

    @Query("SELECT * FROM Task WHERE date = :date ORDER BY time ASC")
    fun getTask(date: String): Single<List<Task>>

    @Query("UPDATE Task SET status = :status WHERE id = :id")
    fun update(status: Boolean, id: Int)

    @Insert(onConflict = REPLACE)
    fun insertTask(task: Task)
}