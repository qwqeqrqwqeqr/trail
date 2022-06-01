package kr.ac.kgu.app.trail.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.data.model.UserInfo
import kr.ac.kgu.app.trail.databinding.FragmentHomeBinding
import kr.ac.kgu.app.trail.ui.base.BaseFragment
import kr.ac.kgu.app.trail.ui.base.InitView
import kr.ac.kgu.app.trail.ui.base.viewBinding
import kr.ac.kgu.app.trail.util.DataState
import net.daum.mf.map.api.MapView

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
    private fun bindUserInfoText(useInfo: UserInfo){
        binding.distanceText.text =useInfo.distanceTotal.toString()
        binding.timeText.text =useInfo.timeTotal.toString()
        binding.stepCounterText.text =useInfo.stepCountTotal.toString()
        binding.nameRewardText.text =useInfo.name.toString()
        binding.nameTitleText.text =useInfo.name.toString()
    }

    override fun initListeners() {

    }


}
