package com.sathish.pmapp.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sathish.pmapp.R
import com.sathish.pmapp.database.entity.Project
import com.sathish.pmapp.database.repository.ProjectManagementRepository
import com.sathish.pmapp.utils.BaseViewModel
import com.sathish.pmapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddProjectViewModel(
    private var repository: ProjectManagementRepository,
    private val resource: Resource
) : BaseViewModel() {

    val name = MutableLiveData<String>()
    val desc = MutableLiveData<String>()


    fun onSaveClick() = viewModelScope.launch(Dispatchers.IO) {
        val projectName = name.value.toString()
        val desc = desc.value.toString()
        if (projectName.isEmpty() && desc.isEmpty()) {
            errorMessage.postValue(resource.getString(R.string.str_error_status_valid))
        } else {
            val project = Project(projectName = projectName, description = desc)
            repository.insertProject(project)
            successMessage.postValue(resource.getString(R.string.str_success_status_project))
        }
    }
}

