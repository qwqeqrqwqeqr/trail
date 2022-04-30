package kr.ac.kgu.app.trail.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.ac.kgu.app.trail.data.datasource.local.dao.UserInfoDao
import kr.ac.kgu.app.trail.data.datasource.local.entity.UserInfoEntity


@Database(entities = [UserInfoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {



    abstract fun GetUserInfoDao(): UserInfoDao




}