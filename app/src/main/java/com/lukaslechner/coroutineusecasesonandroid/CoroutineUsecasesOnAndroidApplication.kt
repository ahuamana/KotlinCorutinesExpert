package com.lukaslechner.coroutineusecasesonandroid

import android.app.Application
import android.content.pm.ApplicationInfo
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase14.AndroidVersionDatabase
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase14.AndroidVersionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

class CoroutineUsecasesOnAndroidApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    val androidVersionRepository by lazy {
        val database = AndroidVersionDatabase.getInstance(applicationContext).androidVersionDao()
        AndroidVersionRepository(
            database,
            applicationScope
        )
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        val isDebuggable: Boolean = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE

        // Enable Debugging for Kotlin Coroutines in debug builds
        // Prints Coroutine name when logging Thread.currentThread().name
        System.setProperty("kotlinx.coroutines.debug", if (isDebuggable) "on" else "off")
    }
}