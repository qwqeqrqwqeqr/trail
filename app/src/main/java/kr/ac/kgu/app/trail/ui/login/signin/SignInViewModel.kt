package kr.ac.kgu.app.trail.ui.login.signin

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.ac.kgu.app.trail.data.datasource.local.dao.UserInfoDao
import kr.ac.kgu.app.trail.data.model.KakaoUserInfo
import kr.ac.kgu.app.trail.data.model.kakaoUserToUserKakaoInfoEntity
import kr.ac.kgu.app.trail.di.SchedulerProvider
import kr.ac.kgu.app.trail.repository.KaKaoRepository
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