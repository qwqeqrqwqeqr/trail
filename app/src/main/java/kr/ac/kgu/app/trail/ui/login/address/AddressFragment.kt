package kr.ac.kgu.app.trail.ui.login.address

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.ui.base.InitView
import kr.ac.kgu.app.trail.ui.base.viewBinding
import kr.ac.kgu.app.trail.ui.login.address.bottomsheet.AddressBottomSheetFragment
import kr.ac.kgu.app.trail.ui.main.MainActivity
import kr.ac.kgu.app.trail.util.DataState
import kr.ac.kgu.app.trail.util.toast
@AndroidEntryPoint
class AddressFragment : Fragment(R.layout.fragment_address), InitView {

    private val viewModel: AddressViewModel by viewModels()
    private val binding by viewBinding(kr.ac.kgu.app.trail.databinding.FragmentAddressBinding::bind)


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
         binding.addressSelectBtn.setOnClickListener {
             viewModel.getAddressList()
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
                    viewModel.signIn()
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

        viewModel.getAddressListLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataState.Error -> {
                    binding.progressBar.isVisible = false
                }
                is DataState.Success -> {
                    binding.progressBar.isVisible = false
                    showAddressBottomSheet(result.data)
                }
                DataState.Loading -> binding.progressBar.isVisible = true
            }
        }
        viewModel.selectedAddressLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                "" -> {
                    binding.addressNextBtn.isEnabled =false
                    binding.addressNextBtn.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.unableColor))
                }
                else -> {
                    binding.addressNextBtn.isEnabled =true
                    binding.addressNextBtn.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.primaryColor))
                }
            }
        }
    }


    private fun showAddressBottomSheet(addressEntryList : List<String>) {
        val bottomSheetFragment = AddressBottomSheetFragment(requireContext(),addressEntryList)
        bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        bottomSheetFragment.setOnClickListener(object :AddressBottomSheetFragment.onBottomSheetClickListener{
            override fun onClick(item: String) {
                viewModel.selectAddress(item)
            }
        })
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