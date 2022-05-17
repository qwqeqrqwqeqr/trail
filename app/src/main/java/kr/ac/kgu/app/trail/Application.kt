package kr.ac.kgu.app.trail

import android.app.Application
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import com.kakao.sdk.common.util.Utility
import kr.ac.kgu.app.trail.util.Constants.NATIVE_APP_KEY
import timber.log.Timber
import java.security.MessageDigest


@HiltAndroidApp
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, NATIVE_APP_KEY)

        getAppKeyHash()
        setupTimber()
        Log.d("hashkey", Utility.getKeyHash(this))
    }


    fun getAppKeyHash() {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for(i in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(i.toByteArray())

                val something = String(Base64.encode(md.digest(), 0)!!)
                Log.e("Debug key", something)
            }
        } catch(e: Exception) {
            Log.e("Not found", e.toString())
        }
    }


    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}