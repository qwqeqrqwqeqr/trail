package kr.ac.kgu.app.trail.data.service.kakao

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import kr.ac.kgu.app.trail.MainActivity

object KakaoCallback {

    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            when {
                error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                    Log.d("kakao", "접근이 거부 됨(동의 취소)")
                }
                error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                    Log.d("kakao", "유효하지 않은 앱")
                      }
                error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                    Log.d("kakao", "인증 수단이 유효하지 않아 인증할 수 없는 상태")
                   }
                error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                    Log.d("kakao", "요청 파라미터 오류")
                     }
                error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                    Log.d("kakao", "유효하지 않은 scope ID")
                     }
                error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                    Log.d("kakao", "설정이 올바르지 않음(android key hash)")
                      }
                error.toString() == AuthErrorCause.ServerError.toString() -> {
                    Log.d("kakao", "\"서버 내부 에러")
                       }
                error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                    Log.d("kakao", "앱이 요청 권한이 없음")
                     }
                else -> {
                    Log.d("kakao", "에러")
                    OAuthToken
                }

            }
        }
        if (token != null) {
            Log.d("kakao", "로그인에 성공하였습니다. $token")
        }
    }
}