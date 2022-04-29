package kr.ac.kgu.app.trail.data.datasource.local.datastore

import kotlinx.coroutines.flow.Flow

interface AppDataStore {
    suspend  fun setAccessToken(value: String)
    suspend fun getAccessToken(): Flow<String>


    suspend  fun setRefreshToken(value: String)
    suspend fun getRefreshToken(): Flow<String>

    suspend fun getId():Flow<String>
    suspend fun setId(value: String)
}