package kr.ac.kgu.app.trail.data.model

import kr.ac.kgu.app.trail.data.datasource.local.entity.UserInfoEntity
import kr.ac.kgu.app.trail.data.datasource.remote.auth.signup.SignUpResponseDto

data class UserInfo(
    var id:String,
    var snsId : String,
    var email: String,
    var nickname: String,
    var thumbnailImageUrl: String,
)



fun UserInfo.UserInfoToUserInfoEntity(): UserInfoEntity = UserInfoEntity(
    id=id,
    snsId=snsId,
    email=email,
    nickname=nickname,
    thumbnailImageUrl=thumbnailImageUrl
)
