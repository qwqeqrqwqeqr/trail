package kr.ac.kgu.app.trail.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.data.model.UserInfo
import kr.ac.kgu.app.trail.databinding.FragmentHomeBinding
import kr.ac.kgu.app.trail.ui.base.BaseFragment
import kr.ac.kgu.app.trail.ui.base.InitView
import kr.ac.kgu.app.trail.ui.base.viewBinding
import kr.ac.kgu.app.trail.util.Constants
import kr.ac.kgu.app.trail.util.DataState
import kotlin.math.round

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel,DataState<UserInfo>>(
    R.layout.fragment_home,
    HomeViewModel::class.java
),InitView {


    private val binding by viewBinding(FragmentHomeBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()
        initUi()
        initListeners()
    }
    override fun updateUi(model: DataState<UserInfo>) {
        when (model) {
            is DataState.Success -> {
                bindUserInfoText(model.data)
                binding.progressBar.isVisible = false
            }
            is DataState.Error -> {
                showErrorDialog()
                binding.progressBar.isVisible = false
            }
            DataState.Loading -> {
                binding.progressBar.isVisible = true
            }
        }
    }

    private fun subscribeToObservers(){
        
    }


    override fun initUi() {

    }
    @SuppressLint("SetTextI18n")
    private fun bindUserInfoText(useInfo: UserInfo){
        binding.distanceText.text = (round(useInfo.distanceTotal*0.001)/10).toString()
        binding.timeText.text =useInfo.timeTotal.toString()
        binding.stepCounterText.text =(useInfo.stepCountTotal).toString()
        binding.nameRewardText.text =useInfo.name.toString()
        binding.nameTitleText.text =useInfo.name.toString()
    }

    override fun initListeners() {
        binding.homeViewWebServiceLayoutBtn.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(Constants.BASE_URL))
            startActivity(browserIntent)
        }
        binding.homeViewWebUseLayoutBtn.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(Constants.BASE_URL))
            startActivity(browserIntent)
        }
    }


}
