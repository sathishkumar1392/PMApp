package com.sathish.pmapp.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sathish.pmapp.database.entity.Project
import com.sathish.pmapp.database.repository.ProjectManagementRepository

class ProjectViewModel(
    private var repository: ProjectManagementRepository

) : ViewModel() {

    var allProjects: LiveData<List<Project>> = MutableLiveData<List<Project>>()

    init {
        getAllProject()
    }


    private fun getAllProject() {
        allProjects = repository.allProjects
    }


}