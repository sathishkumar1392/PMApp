package com.sathish.pmapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sathish.pmapp.database.entity.Comment
import com.sathish.pmapp.database.entity.Project
import com.sathish.pmapp.database.entity.Task

@Dao
interface ProjectManagementDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProject(project: Project)

    @Query("SELECT * FROM  project_table ")
    fun getAllProjects(): LiveData<List<Project>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTask(task: Task) : Long

    @Query("SELECT * FROM task_table WHERE prjReferenceId = :prjId")
    fun getAllTasks(prjId:Int): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(comment:Comment)

    @Query("SELECT * FROM comment_table WHERE taskId = :taskId")
    fun getComments(taskId:Int):LiveData<List<Comment>>

    @Update
    fun updateTask(task : Task)

    @Query("UPDATE TASK_TABLE SET Status = :status WHERE taskId = :id")
    fun updateTaskStatus(id:Int,status:String)
}