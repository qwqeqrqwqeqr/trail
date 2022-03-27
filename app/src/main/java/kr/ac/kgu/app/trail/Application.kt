package kr.ac.kgu.app.trail

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import com.kakao.sdk.common.util.Utility
import kr.ac.kgu.app.trail.util.Constants.NATIVE_APP_KEY


@HiltAndroidApp
class Application: Application(){

    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, NATIVE_APP_KEY)

        Log.d("hashkey", Utility.getKeyHash(this))
    }
}