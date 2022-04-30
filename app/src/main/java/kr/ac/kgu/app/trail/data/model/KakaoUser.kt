package kr.ac.kgu.app.trail.data.model

import kr.ac.kgu.app.trail.data.datasource.remote.auth.signin.SignInRequestDto
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signup.SignUpRequestDto
import java.net.URL


data class KakaoUser(
    var id:Long?, //회원번호
    var email: String?, //이메일
    var nickname: String?, //이름
    var thumbnailImageUrl: String? // 프로필 사진
)

fun KakaoUser.KakaoUserToSignUpRequestDto(): SignUpRequestDto = SignUpRequestDto(snsId= id.toString(),name= nickname.toString())


