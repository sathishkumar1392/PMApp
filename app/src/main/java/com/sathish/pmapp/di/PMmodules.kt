package com.sathish.pmapp.di

import com.sathish.pmapp.database.ProjectManagementDataBase
import com.sathish.pmapp.database.repository.ProjectManagementRepository
import com.sathish.pmapp.database.repository.TaskRepository
import com.sathish.pmapp.project.AddProjectViewModel
import com.sathish.pmapp.project.ProjectAdapter
import com.sathish.pmapp.project.ProjectViewModel
import com.sathish.pmapp.task.AddTaskViewModel
import com.sathish.pmapp.task.TaskAdapter
import com.sathish.pmapp.task.TaskViewModel
import com.sathish.pmapp.taskdetails.TaskDetailViewModel
import com.sathish.pmapp.utils.Resource
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val dbModule = module {
    single { ProjectManagementDataBase.getInstance(androidContext())}
    factory { get<ProjectManagementDataBase>().projectManagementDao() }
}

val repositoryModule = module {
    single { ProjectManagementRepository(get()) }
    single { TaskRepository(get()) }

}

val projectModule = module {
    viewModel { ProjectViewModel(get()) }
    viewModel { AddProjectViewModel(get(),get()) }
    factory { ProjectAdapter() }
    single { Resource(androidContext()) }

    viewModel { TaskViewModel(get()) }
    viewModel { AddTaskViewModel(get(),get()) }
    factory { TaskAdapter() }

    viewModel { TaskDetailViewModel(get(),get()) }
}


