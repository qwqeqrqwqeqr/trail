package kr.ac.kgu.app.trail.data.datasource.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.map
import kr.ac.kgu.app.trail.data.datasource.local.LocalDataConstants

import kotlinx.coroutines.flow.Flow

interface AppDataStore {

    suspend fun readInt(key: String):Flow<Int>
    suspend fun setInt(key: String,value: Int)

    suspend fun readString(key: String):Flow<String>
    suspend fun setString(key: String,value: String)
}

class AppDataStoreImpl(private val context: Context):AppDataStore {


    private val Context.dataStore : DataStore<Preferences> by  preferencesDataStore(
        LocalDataConstants.APP_DATASTORE)



    override suspend fun readInt(key: String): Flow<Int> {
        return context.dataStore.data.map { preference ->
            preference[intPreferencesKey(key)] ?: 0
        }
    }

    override suspend fun setInt(key: String, value: Int) {
        context.dataStore.edit {
            it[intPreferencesKey(key)] = value
        }
    }

    override suspend fun readString(key: String): Flow<String> {
        return context.dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)] ?: ""
        }
    }

    override suspend fun setString(key: String, value: String) {
        context.dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }
}