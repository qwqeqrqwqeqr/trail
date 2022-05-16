package kr.ac.kgu.app.trail.ui.auth.signin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kakao.sdk.user.RxUserApiClient
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.databinding.FragmentSignInBinding
import kr.ac.kgu.app.trail.ui.base.InitView
import kr.ac.kgu.app.trail.ui.base.viewBinding
import kr.ac.kgu.app.trail.ui.main.MainActivity
import kr.ac.kgu.app.trail.util.DataState
import kr.ac.kgu.app.trail.util.showToast
import kr.ac.kgu.app.trail.util.toast
import timber.log.Timber

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) , InitView {


    private val viewModel: SignInViewModel by viewModels()
    private val binding by viewBinding(FragmentSignInBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initListeners()

    }

    override fun initUi() {

    }

    @SuppressLint("CheckResult")
    override fun initListeners() {
        binding.loginKakaoLoginBtn.setOnClickListener {
            RxUserApiClient.instance.loginWithKakaoTalk(requireActivity())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewModel.saveUserInfo()
                navigateAddress()
            }, {
                requireActivity().showToast(it.message.toString())
            })

        }
    }




    private fun navigateAddress() {
        Navigation.findNavController(requireActivity(),R.id.login_fragment_container).navigate(
            SignInFragmentDirections.actionSignInToAddress()
        )
    }

}