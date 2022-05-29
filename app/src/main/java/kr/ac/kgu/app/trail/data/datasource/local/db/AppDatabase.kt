package kr.ac.kgu.app.trail.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.ac.kgu.app.trail.data.datasource.local.dao.CourseDao
import kr.ac.kgu.app.trail.data.datasource.local.dao.UserInfoDao
import kr.ac.kgu.app.trail.data.datasource.local.entity.CourseEntity
import kr.ac.kgu.app.trail.data.datasource.local.entity.KakaoUserInfoEntity


@Database(entities = [KakaoUserInfoEntity::class, CourseEntity::class], version = 4, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {



    abstract fun UserInfoDao(): UserInfoDao
    abstract fun CourseDao() : CourseDao




}