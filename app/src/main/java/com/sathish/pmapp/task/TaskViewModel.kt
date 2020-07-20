package com.sathish.pmapp.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sathish.pmapp.database.entity.Task
import com.sathish.pmapp.database.repository.TaskRepository
import com.sathish.pmapp.utils.BaseViewModel

class TaskViewModel(private var repo: TaskRepository) : BaseViewModel() {

    var allTask: LiveData<List<Task>> = MutableLiveData<List<Task>>()


    fun getTaskList(id: Int) {
        allTask = repo.getTasks(id)
    }

}
