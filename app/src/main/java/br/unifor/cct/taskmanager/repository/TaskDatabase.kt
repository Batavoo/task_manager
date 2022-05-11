package br.unifor.cct.taskmanager.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import br.unifor.cct.taskmanager.entity.Task
import br.unifor.cct.taskmanager.entity.User


@Database(entities = [User::class, Task::class], version = 3)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun getUserDAO(): UserDAO
    abstract fun getTaskDAO(): TaskDAO
}