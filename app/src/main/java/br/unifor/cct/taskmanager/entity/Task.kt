package br.unifor.cct.taskmanager.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "tasks"
)
data class Task(
    @PrimaryKey
    val id:Int? = null,
    val name:String,
    val description:String,
    @ColumnInfo(name = "is_finished")
    val isFinished:Boolean,
    @ColumnInfo(name = "user_id")
    val userId:Int
)
