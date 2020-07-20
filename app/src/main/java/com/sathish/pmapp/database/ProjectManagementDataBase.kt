package com.sathish.pmapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sathish.pmapp.database.dao.ProjectManagementDao
import com.sathish.pmapp.database.entity.Comment
import com.sathish.pmapp.database.entity.Project
import com.sathish.pmapp.database.entity.Task

@Database(entities = [Project::class,Task::class,Comment::class], version = 3,exportSchema = false)
abstract class ProjectManagementDataBase : RoomDatabase() {
    abstract fun projectManagementDao(): ProjectManagementDao

    companion object {

        private var instance: ProjectManagementDataBase? = null

        fun getInstance(context: Context): ProjectManagementDataBase {
            if (instance == null) {
                synchronized(ProjectManagementDataBase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProjectManagementDataBase::class.java, "projectManagement_database"
                    )
                        .build()
                }
            }
            return instance!!
        }

    }

}