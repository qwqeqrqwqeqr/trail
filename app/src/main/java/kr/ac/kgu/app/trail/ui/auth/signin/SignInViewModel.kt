package kr.ac.kgu.app.trail.ui.auth.signin

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.RxUserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.ac.kgu.app.trail.data.datasource.local.dao.UserInfoDao
import kr.ac.kgu.app.trail.data.model.KakaoUserInfo
import kr.ac.kgu.app.trail.data.model.kakaoUserToUserKakaoInfoEntity
import kr.ac.kgu.app.trail.di.DispatcherProvider
import kr.ac.kgu.app.trail.di.SchedulerProvider
import kr.ac.kgu.app.trail.repository.AuthRepository
import kr.ac.kgu.app.trail.repository.KaKaoRepository
import kr.ac.kgu.app.trail.util.DataState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject constructor(
    private val kaKaoRepository: KaKaoRepository,
    private val schedulerProvider: SchedulerProvider,
    private val userInfoDao: UserInfoDao
    ): ViewModel(){


    @SuppressLint("CheckResult")
    fun saveUserInfo(){
        kaKaoRepository.saveUserInfo()
            .subscribeOn(schedulerProvider.ioScheduler)
            .observeOn(schedulerProvider.subScheduler)
            .subscribe ({ it ->
                userInfoDao.insertUserinfo(
                    KakaoUserInfo(
                        it.id.toString(),
                        it.kakaoAccount?.email.toString(),
                        it.kakaoAccount?.profile?.nickname.toString()
                    ).kakaoUserToUserKakaoInfoEntity()
                )
                Timber.i("save userinfo id?: "+it.id)
                Timber.i("save userinfo email?: "+it.kakaoAccount?.email.toString())
                Timber.i("save userinfo name?: "+ it.kakaoAccount?.profile?.nickname.toString())

            }, {
                Timber.d(it.message.toString())
            })
    }





}