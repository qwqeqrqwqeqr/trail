package kr.ac.kgu.app.trail.data.service.kakao

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kr.ac.kgu.app.trail.data.model.KakaoUser
import kr.ac.kgu.app.trail.util.Constants.KAKAO
import javax.inject.Inject

class KakaoUserServiceImpl @Inject constructor(private val context: Context) : KakaoUserService {

    override suspend fun loginWithKakaoAccount() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e(KAKAO, "카카오톡으로 로그인 실패", error)
                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(
                        context,
                        callback = KakaoCallback.callback
                    )
                } else if (token != null) {
                    Log.i(KAKAO, "카카오톡으로 로그인 성공 ${token.accessToken}")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = KakaoCallback.callback)
        }
    }


    override suspend fun kakaoHasToken(): Boolean = AuthApiClient.instance.hasToken()

//    if (AuthApiClient.instance.hasToken()) {
//        UserApiClient.instance.accessTokenInfo { _, error ->
//            if (error != null) {
//                if (error is KakaoSdkError && error.isInvalidTokenError()) {
//                    //로그인 필요
//                } else {
//                    //기타 에러
//                }
//            } else {
//            }
//        }
//    } else {
//        //로그인 필요
//    }

    override suspend fun accessTokenInfo() {

        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Log.e(KAKAO, "토큰 정보 보기 실패", error)
            } else if (tokenInfo != null) {
                Log.i(
                    KAKAO, "토큰 정보 보기 성공" +
                            "\n회원번호: ${tokenInfo.id}" +
                            "\n만료시간: ${tokenInfo.expiresIn} 초"
                )
            }
        }
    }

    override suspend fun unlink() {
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e(KAKAO, "연결 끊기 실패", error)
            } else {
                Log.i(KAKAO, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
            }
        }
    }

    override suspend fun logout() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(KAKAO, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            } else {
                Log.i(KAKAO, "로그아웃 성공. SDK에서 토큰 삭제됨")
            }
        }
    }


    override suspend fun getUserInfo(): Flow<KakaoUser> = callbackFlow {
        UserApiClient.instance.me { user, error ->
            Log.i(
                KAKAO, "사용자 정보 요청 성공" +
                        "\n회원번호: ${user?.id}" +
                        "\n이메일: ${user?.kakaoAccount?.email}" +
                        "\n닉네임: ${user?.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user?.kakaoAccount?.profile?.thumbnailImageUrl}"
            )
            trySend(
                KakaoUser(
                    user?.id!!,
                    user.kakaoAccount?.email!!,
                    user.kakaoAccount?.profile?.nickname!!,
                    user.kakaoAccount?.profile?.thumbnailImageUrl!!
                )
            )
        }


    }
}