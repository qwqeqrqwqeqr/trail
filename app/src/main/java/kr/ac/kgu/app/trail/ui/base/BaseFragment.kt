package kr.ac.kgu.app.trail.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.ac.kgu.app.trail.R

abstract class BaseFragment<T : BaseViewModel<BaseModelData>, BaseModelData>(
    layoutId: Int, private val viewModelClass: Class<T>
) : Fragment(layoutId) {

    internal lateinit var viewModel: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(viewModelClass)
        viewModel.fetchInitialData()
        subscribeToObservers()
    }

    abstract fun updateUi(model: BaseModelData)

    fun showNoInternetConnectionToast() {
        Toast.makeText(
            requireContext(),
            getString(R.string.test),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun showErrorDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.test))
            .setMessage(getString(R.string.test))
            .setPositiveButton(getString(R.string.test)) { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun subscribeToObservers() {
        viewModel.modelLiveData.observe(viewLifecycleOwner) {
            updateUi(it)
        }
    }
}