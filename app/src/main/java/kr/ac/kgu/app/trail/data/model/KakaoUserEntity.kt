package kr.ac.kgu.app.trail.data.model

import java.net.URL


data class KakaoUserEntity(
    var id:String, //회원번호
    var email: String, //이메일
    var nickname: String, //이름
    var thumbnailImageUrl : URL // 프로필 사진
)