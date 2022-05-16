package kr.ac.kgu.app.trail.data.datasource.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.ac.kgu.app.trail.data.datasource.local.LocalDataConstants


class AppDataStoreManager(private val context: Context):AppDataStore {


    private val Context.dataStore : DataStore<Preferences> by  preferencesDataStore(
        LocalDataConstants.APP_DATASTORE)

    val ID = stringPreferencesKey(LocalDataConstants.ID)


    override suspend fun getId(): Flow<String> {
        return context.dataStore.data.map { preference ->
            preference[ID].toString()
        }
    }

    override suspend fun setId(value: String) {
        context.dataStore.edit { settings -> settings[ID] = value }
    }
}