package kr.ac.kgu.app.trail.repository

import android.annotation.SuppressLint
import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.RxUserApiClient
import com.kakao.sdk.user.model.User
import io.reactivex.Single
import kr.ac.kgu.app.trail.data.datasource.local.dao.UserInfoDao
import kr.ac.kgu.app.trail.data.model.KakaoUserInfo
import kr.ac.kgu.app.trail.data.model.KakaoUserToUserKakaoInfoEntity
import kr.ac.kgu.app.trail.data.service.kakao.KakaoService
import timber.log.Timber
import javax.inject.Inject

interface KaKaoRepository {
    fun saveUserInfo(): Single<User>
}

class KakaoRepositoryImpl @Inject constructor(
    private val kakaoService: KakaoService,
    private val userInfoDao: UserInfoDao
) : KaKaoRepository {



    override fun saveUserInfo():Single<User> = kakaoService.getUserInfo()





}