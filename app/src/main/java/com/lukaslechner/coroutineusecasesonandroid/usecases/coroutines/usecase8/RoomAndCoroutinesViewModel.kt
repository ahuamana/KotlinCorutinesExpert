package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase8

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase14.mapToEntityList
import kotlinx.coroutines.launch

class RoomAndCoroutinesViewModel(
    private val api: MockApi,
    private val database: AndroidVersionDao
) : BaseViewModel<UiState>() {

    fun loadData() {
        uiState.value = UiState.Loading.LoadFromDb
        viewModelScope.launch {
            val localVersions = database.getAndroidVersions()
            if (localVersions.isNotEmpty()) {
                uiState.value = UiState.Error(DataSource.DATABASE, "Databse empty")
            } else {
                uiState.value = UiState.Success(DataSource.DATABASE, localVersions.mapToUiModelList())
            }

            uiState.value = UiState.Loading.LoadFromNetwork
            try {
                val recentVersion = api.getRecentAndroidVersions()
                for (version in recentVersion) {
                    database.insert(version.mapToEntity())
                }
                uiState.value = UiState.Success(DataSource.NETWORK, recentVersion)
            }catch (e: Exception){
                uiState.value = UiState.Error(DataSource.NETWORK, e.message ?: "Unknown error")
            }

        }

    }

    fun clearDatabase() {
        viewModelScope.launch {
            database.clear()
        }
    }
}

enum class DataSource(val dataSourceName: String) {
    DATABASE("Database"),
    NETWORK("Network")
}