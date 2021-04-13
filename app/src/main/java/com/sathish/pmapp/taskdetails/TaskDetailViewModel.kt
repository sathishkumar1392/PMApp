package com.sathish.pmapp.taskdetails

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sathish.pmapp.R
import com.sathish.pmapp.database.entity.Comment
import com.sathish.pmapp.database.entity.Task
import com.sathish.pmapp.database.repository.TaskRepository
import com.sathish.pmapp.utils.BaseViewModel
import com.sathish.pmapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TaskDetailViewModel(private val repo: TaskRepository, private val resource: Resource) :
    BaseViewModel() {

    var taskId = 0
    val commentStr = MutableLiveData<String>()
    var allComments: LiveData<List<Comment>> = MutableLiveData()
    var taskStatus: String? = null


    fun updateTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {

        if (task.taskName.toString().isEmpty() && task.description.toString().isEmpty()) {
            errorMessage.postValue(resource.getString(R.string.str_error_task))
        } else {
            repo.updateTask(task)
            successMessage.postValue(resource.getString(R.string.str_update_status_task))
        }
    }

    fun saveComment() = viewModelScope.launch(Dispatchers.IO) {
        val strComment = commentStr.value.toString()
        if (strComment.isNotEmpty()) {
            val comment = Comment(taskId = taskId, comment = strComment)
            repo.insertComment(comment)
            successMessage.postValue(resource.getString(R.string.str_comment_status))
        }
    }

    fun sendTaskToQA() = viewModelScope.launch(Dispatchers.IO) {
        if (taskStatus == "InReview") {
            errorMessage.postValue(resource.getString(R.string.str_task_review))
        } else {
            repo.updateTaskStatus(id = taskId, status = "InReview")
            successMessage.postValue(resource.getString(R.string.str_task_qa))
        }

    }

    fun getComments() {
        allComments = repo.getComments(taskId)
    }


    @SuppressLint("NewApi")
    fun getModifiedDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        return formatted.toString()
    }
}