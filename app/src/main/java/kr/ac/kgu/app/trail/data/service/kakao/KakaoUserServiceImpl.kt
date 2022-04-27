package kr.ac.kgu.app.trail.data.service.kakao

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.ac.kgu.app.trail.MainActivity
import kr.ac.kgu.app.trail.util.Constants.KAKAO
import kr.ac.kgu.app.trail.util.DataState
import javax.inject.Inject

class KakaoUserServiceImpl @Inject constructor(private val context: Context) : KakaoUserService {

    override suspend fun loginWithKakaoAccount(): Flow<DataState<Unit>> =
        flow {
            emit(DataState.Loading)
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
                        UserApiClient.instance.loginWithKakaoAccount(context, callback = KakaoCallback.callback)
                    } else if (token != null) {
                        Log.i(KAKAO, "카카오톡으로 로그인 성공 ${token.accessToken}") } }
                emit(DataState.Success(Unit))
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = KakaoCallback.callback)
                emit(DataState.Error())
            }
        }


    override suspend fun KakaoHasToken() {
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        //로그인 필요
                    } else {
                        //기타 에러
                    }
                } else {
                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                }
            }
        } else {
            //로그인 필요
        }
    }

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

    override suspend fun getUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(KAKAO, "사용자 정보 요청 실패", error)
            } else if (user != null) {
                Log.i(
                    KAKAO, "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )
            }
        }
    }

}