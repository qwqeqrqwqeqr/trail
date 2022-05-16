package kr.ac.kgu.app.trail.data.datasource.local.datastore

import kotlinx.coroutines.flow.Flow

interface AppDataStore {

    suspend fun getId():Flow<String>
    suspend fun setId(value: String)
}