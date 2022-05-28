package kr.ac.kgu.app.trail.ui.login.signin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kakao.sdk.user.RxUserApiClient
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.databinding.FragmentSignInBinding
import kr.ac.kgu.app.trail.ui.base.InitView
import kr.ac.kgu.app.trail.ui.base.viewBinding
import kr.ac.kgu.app.trail.util.showToast

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