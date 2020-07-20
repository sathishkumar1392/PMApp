package com.sathish.pmapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_table")
data class Project(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "projectName") val projectName: String?,
    @ColumnInfo(name = "description") val description: String?
)

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true) val taskId: Int = 0,
    @ColumnInfo(name = "prjReferenceId")
    val prjReferenceId: Int = 0,
    @ColumnInfo(name = "taskName") val taskName: String?,
    @ColumnInfo(name = "taskDescription") val description: String?
    ,
    @ColumnInfo(name = "createdDate") val createdDate: String?,
    @ColumnInfo(name = "Status") val Status: String?
)


@Entity(tableName = "comment_table")
data class Comment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "taskId") val taskId: Int = 0,
    val comment: String
)
