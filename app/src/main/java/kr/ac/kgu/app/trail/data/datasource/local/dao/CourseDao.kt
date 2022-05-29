package kr.ac.kgu.app.trail.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.ac.kgu.app.trail.data.datasource.local.entity.CourseEntity

@Dao
interface CourseDao {

    @Query("SELECT * FROM course WHERE courseId = :id")
    suspend fun getCourse(id: Int): CourseEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(courseEntity: CourseEntity)
}