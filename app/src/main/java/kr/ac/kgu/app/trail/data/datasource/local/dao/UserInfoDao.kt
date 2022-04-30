package kr.ac.kgu.app.trail.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.ac.kgu.app.trail.data.datasource.local.entity.UserInfoEntity

@Dao
interface UserInfoDao {
    @Query("SELECT * FROM user_info WHERE id = :id")
    suspend fun searchById(id: String): UserInfoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAndReplace(userInfoEntity: UserInfoEntity): Long

}