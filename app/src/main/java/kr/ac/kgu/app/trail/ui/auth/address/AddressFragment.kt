package kr.ac.kgu.app.trail.ui.auth.address

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.databinding.FragmentAddressBinding
import kr.ac.kgu.app.trail.ui.base.BaseFragment
import kr.ac.kgu.app.trail.ui.base.InitView
import kr.ac.kgu.app.trail.ui.base.viewBinding
import kr.ac.kgu.app.trail.ui.main.MainActivity
import kr.ac.kgu.app.trail.util.DataState
import kr.ac.kgu.app.trail.util.toast
@AndroidEntryPoint
class AddressFragment : Fragment(R.layout.fragment_address), InitView {

    private val viewModel: AddressViewModel by viewModels()
    private val binding by viewBinding(FragmentAddressBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initListeners()
        subscribeToObservers()
    }

     override fun initUi(){}

     override fun initListeners(){
        binding.addressNextBtn.setOnClickListener {
            viewModel.signUp()
        }
    }

    private fun subscribeToObservers() {
        viewModel.signUpLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataState.Error -> {
                    binding.progressBar.isVisible = false
                    this.toast(resources.getString(R.string.sign_up_error_toast_message_text))
                }
                is DataState.Success -> {
                    binding.progressBar.isVisible = false
//                    viewModel.signIn()
                    navigateMainScreen()
                }
                DataState.Loading -> binding.progressBar.isVisible = true
            }
        }

        viewModel.signInLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataState.Error -> {
                    binding.progressBar.isVisible = false
                    this.toast(resources.getString(R.string.sign_up_error_toast_message_text))
                }
                is DataState.Success -> {
                    binding.progressBar.isVisible = false
                    navigateMainScreen()
                }
                DataState.Loading -> binding.progressBar.isVisible = true
            }
        }
    }



    private fun navigateMainScreen() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
    }


    private fun showNoInternetConnectionToast() {
        Toast.makeText(
            requireContext(),
            getString(R.string.test),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showErrorDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.test))
            .setMessage(getString(R.string.test))
            .setPositiveButton(getString(R.string.test)) { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }


}