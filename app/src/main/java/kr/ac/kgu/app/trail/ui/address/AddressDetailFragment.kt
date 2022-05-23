package kr.ac.kgu.app.trail.ui.address

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.data.model.AddressEntry
import kr.ac.kgu.app.trail.databinding.FragmentAddressBinding
import kr.ac.kgu.app.trail.ui.base.BaseFragment
import kr.ac.kgu.app.trail.ui.base.viewBinding
import kr.ac.kgu.app.trail.util.DataState


@AndroidEntryPoint
class AddressDetailFragment : BaseFragment<AddressDetailViewModel, DataState<List<AddressEntry>>>(
    R.layout.fragment_address_detail,AddressDetailViewModel::class.java) {

    private val binding by viewBinding(FragmentAddressBinding::bind)
    private val addressDetailViewModel: AddressDetailViewModel by activityViewModels()
//    private lateinit var addressAdapter: AddressAdapter


    override fun updateUi(model: DataState<List<AddressEntry>>) {

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        addressAdapter = AddressAdapter(requireContext())
    }

}