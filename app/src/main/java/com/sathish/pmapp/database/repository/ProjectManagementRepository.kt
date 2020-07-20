package com.sathish.pmapp.database.repository

import com.sathish.pmapp.database.dao.ProjectManagementDao
import com.sathish.pmapp.database.entity.Project


class ProjectManagementRepository(private val dao: ProjectManagementDao) {

    val allProjects = dao.getAllProjects()


    fun insertProject(project: Project) {
        return dao.insertProject(project)
    }


}
