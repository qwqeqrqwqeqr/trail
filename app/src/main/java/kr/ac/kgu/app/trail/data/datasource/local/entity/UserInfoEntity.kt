package kr.ac.kgu.app.trail.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_info")
data class UserInfoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "sns_id")
    var snsId : String, //회원번호

    @ColumnInfo(name = "email")
    var email: String, //이메일

    @ColumnInfo(name = "nickname")
    var nickname: String, //이름
    
    @ColumnInfo(name = "thumbnail_image_url")
    var thumbnailImageUrl: String // 프로필 사진
)