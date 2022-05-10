package kr.ac.kgu.app.trail.data.service.kakao

import kotlinx.coroutines.flow.Flow
import kr.ac.kgu.app.trail.data.model.KakaoUser

interface KakaoUserService {

    suspend fun loginWithKakaoAccount()
    suspend fun kakaoHasToken() : Boolean
    suspend fun accessTokenInfo()
    suspend fun unlink()
    suspend fun logout()
    suspend fun getUserInfo(): Flow<KakaoUser>

}