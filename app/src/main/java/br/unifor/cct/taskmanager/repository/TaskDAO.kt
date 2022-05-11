package br.unifor.cct.taskmanager.repository

import androidx.room.*
import br.unifor.cct.taskmanager.entity.Task

@Dao
interface TaskDAO {

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM tasks WHERE id = id")
    fun findById(id: Int):Task

    @Query("SELECT * FROM tasks")
    fun findAll():List<Task>


}