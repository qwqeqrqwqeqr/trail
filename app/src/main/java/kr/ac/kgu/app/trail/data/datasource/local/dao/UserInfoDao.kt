package kr.ac.kgu.app.trail.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.ac.kgu.app.trail.data.datasource.local.entity.KakaoUserInfoEntity

@Dao
interface UserInfoDao {
    @Query("SELECT * FROM user_info")
    suspend fun getUserinfo(): Array<KakaoUserInfoEntity>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertUserinfo(kakaoUserInfoEntity: KakaoUserInfoEntity)



}