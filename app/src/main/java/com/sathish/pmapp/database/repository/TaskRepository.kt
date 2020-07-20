package com.sathish.pmapp.database.repository

import androidx.lifecycle.LiveData
import com.sathish.pmapp.database.dao.ProjectManagementDao
import com.sathish.pmapp.database.entity.Comment
import com.sathish.pmapp.database.entity.Task

class TaskRepository(private val dao: ProjectManagementDao) {


    fun getTasks(id: Int): LiveData<List<Task>> {
        return dao.getAllTasks(prjId = id)
    }

    fun insertTask(task: Task): Long {
        return dao.insertTask(task)
    }


    fun insertComment(comment: Comment) {
        return dao.insertComment(comment)
    }

    fun updateTask(task: Task) {
        return dao.updateTask(task)
    }

    fun getComments(id: Int): LiveData<List<Comment>> {
        return dao.getComments(id)
    }

    fun updateTaskStatus(id:Int,status:String){
        return dao.updateTaskStatus(id, status)
    }
}