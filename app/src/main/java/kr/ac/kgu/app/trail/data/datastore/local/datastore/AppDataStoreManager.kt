package kr.ac.kgu.app.trail.data.datastore.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AppDataStoreManager(private val context: Context):AppDataStore {


    private val Context.dataStore : DataStore<Preferences> by  preferencesDataStore(AppDataStoreConstants.APP_DATASTORE)

    val ACCESS_TOKEN = stringPreferencesKey(AppDataStoreConstants.ACCESS_TOKEN)
    val REFRESH_TOKEN = stringPreferencesKey(AppDataStoreConstants.REFRESH_TOKEN)
    val ID = stringPreferencesKey(AppDataStoreConstants.ID)

    override suspend fun setAccessToken(value: String) {
        context.dataStore.edit { settings -> settings[ACCESS_TOKEN] = value }
    }

    override suspend fun getAccessToken(): Flow<String> {
        return context.dataStore.data.map { preference ->
            preference[ACCESS_TOKEN].toString() ?: "none"
        }
    }

    override suspend fun setRefreshToken(value: String) {
        context.dataStore.edit { settings -> settings[REFRESH_TOKEN] = value }
    }

    override suspend fun getRefreshToken(): Flow<String> {
        return context.dataStore.data.map { preference ->
            preference[REFRESH_TOKEN].toString() ?: "none"
        }
    }

    override suspend fun getId(): Flow<String> {
        return context.dataStore.data.map { preference ->
            preference[ID].toString()
        }
    }

    override suspend fun setId(value: String) {
        context.dataStore.edit { settings -> settings[ID] = value }
    }
}