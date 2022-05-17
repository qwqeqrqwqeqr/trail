package kr.ac.kgu.app.trail.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signin.SignInRequestDto
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signup.SignUpRequestDto
import kr.ac.kgu.app.trail.data.model.KakaoUserInfo


@Entity(tableName = "user_info")
data class KakaoUserInfoEntity(
    @PrimaryKey
    @ColumnInfo(name = "sns_id")
    var snsId : String, //회원번호

    @ColumnInfo(name = "email")
    var email: String?, //이메일

    @ColumnInfo(name = "nickname")
    var nickname: String, //이름
)


fun KakaoUserInfoEntity.kakaoUserInfoToSignUpRequestDto(): SignUpRequestDto = SignUpRequestDto(snsId= snsId,name= nickname)
fun KakaoUserInfoEntity.kakaoUserInfoToSignInRequestDto(): SignInRequestDto = SignInRequestDto(snsId= snsId,name= nickname)