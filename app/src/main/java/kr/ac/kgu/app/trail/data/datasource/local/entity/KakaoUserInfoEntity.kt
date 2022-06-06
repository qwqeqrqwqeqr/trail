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


fun KakaoUserInfoEntity.kakaoUserInfoEntityToSignUpRequestDto(): SignUpRequestDto = SignUpRequestDto(snsId= snsId+"1234567",name= nickname)
fun KakaoUserInfoEntity.kakaoUserInfoEntityToSignInRequestDto(): SignInRequestDto = SignInRequestDto(snsId= snsId+"1234567",name= nickname)