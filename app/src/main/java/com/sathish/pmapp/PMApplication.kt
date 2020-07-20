package com.sathish.pmapp

import android.app.Application
import com.sathish.pmapp.di.dbModule
import com.sathish.pmapp.di.projectModule
import com.sathish.pmapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class PMApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PMApplication)
            modules(listOf(dbModule, repositoryModule,projectModule))
        }
    }

}