package br.unifor.cct.taskmanager.repository

import android.content.Context
import androidx.room.Room

object DatabaseUtil {

    private var instance:TaskDatabase? = null

    fun getInstance(context:Context):TaskDatabase{
        if (instance == null){
            instance = Room.databaseBuilder(
                context,
                TaskDatabase::class.java,
            "taskdatabase.db"
            ).fallbackToDestructiveMigration()
                .build()
        }
        return instance!!
    }

}