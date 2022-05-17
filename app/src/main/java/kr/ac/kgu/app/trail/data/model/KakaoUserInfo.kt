package kr.ac.kgu.app.trail.data.model


import kr.ac.kgu.app.trail.data.datasource.local.entity.KakaoUserInfoEntity
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signup.SignUpRequestDto
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signup.SignUpResponseDto

data class KakaoUserInfo(
    var snsId : String,
    var email: String,
    var nickname: String,
)



fun KakaoUserInfo.kakaoUserToUserKakaoInfoEntity(): KakaoUserInfoEntity = KakaoUserInfoEntity(
    snsId=snsId,
    email=email,
    nickname=nickname,
)
