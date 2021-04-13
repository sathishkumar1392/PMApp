package com.sathish.pmapp.task

import android.annotation.SuppressLint
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

class AddTaskViewModel(private var repo: TaskRepository, private val resource: Resource) :
    BaseViewModel() {

    var prjId = 0
    val name = MutableLiveData<String>()
    val desc = MutableLiveData<String>()
    val comment = MutableLiveData<String>()


    fun onSaveClick() = viewModelScope.launch(Dispatchers.IO) {
        val taskName = name.value.toString()
        val desc = desc.value.toString()
        val taskComment = comment.value.toString()

        if (taskName.isEmpty() && desc.isEmpty() && taskComment.isEmpty()) {
            errorMessage.postValue(resource.getString(R.string.str_error_task))
        } else {
            val currentDate = getCurrentDate()
            val task = Task(
                prjReferenceId = prjId,
                taskName = taskName,
                description = desc,
                createdDate = currentDate,
                Status = "New"
            )
            val taskId = repo.insertTask(task)
            val comment = Comment(taskId = taskId.toInt(),comment = taskComment)
            insertComment(comment)
            successMessage.postValue(resource.getString(R.string.str_success_status_task))
        }
    }


      @SuppressLint("NewApi")
      private fun getCurrentDate(): String {
          val current = LocalDateTime.now()
          val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
          val formatted = current.format(formatter)
          return formatted.toString()
      }


    private fun insertComment(comment: Comment) = viewModelScope.launch(Dispatchers.IO) {
        repo.insertComment(comment)
    }

}