package kr.ac.kgu.app.trail.data.service.kakao

import android.content.Intent
import kotlinx.coroutines.flow.Flow
import kr.ac.kgu.app.trail.util.DataState

interface KakaoUserService {

    suspend fun loginWithKakaoAccount(): Flow<DataState<Unit>>
    suspend fun KakaoHasToken()
    suspend fun accessTokenInfo()
    suspend fun unlink()
    suspend fun logout()
    suspend fun getUserInfo()

}