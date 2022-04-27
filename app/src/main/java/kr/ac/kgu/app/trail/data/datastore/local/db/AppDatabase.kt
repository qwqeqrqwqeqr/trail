package kr.ac.kgu.app.trail.data.datastore.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.ac.kgu.app.trail.data.datastore.local.entity.TrailEntity


@Database(entities = [TrailEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {



}