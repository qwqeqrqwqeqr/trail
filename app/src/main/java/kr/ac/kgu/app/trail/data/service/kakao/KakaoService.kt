package kr.ac.kgu.app.trail.data.service.kakao

import android.content.Context
import com.kakao.sdk.navi.model.KakaoNaviParams
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.RxUserApiClient
import com.kakao.sdk.user.RxUserApi
import com.kakao.sdk.user.model.User
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


interface KakaoService {
    fun getUserInfo(): Single<User>
    fun logout(): Completable
}


class KakaoServiceImpl @Inject constructor():KakaoService {

    override fun getUserInfo(): Single<User> = RxUserApiClient.instance.me()
    override fun logout(): Completable = RxUserApiClient.instance.logout()
}