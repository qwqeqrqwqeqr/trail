package kr.ac.kgu.app.trail.data.local.db

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import kr.ac.kgu.app.trail.data.local.entity.TrailEntity


@Database(entities = [TrailEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {



}