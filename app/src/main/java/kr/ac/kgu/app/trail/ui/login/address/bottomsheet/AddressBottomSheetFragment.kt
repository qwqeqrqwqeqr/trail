package kr.ac.kgu.app.trail.ui.login.address.bottomsheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.databinding.FragmentAddressBottomSheetBinding
import kr.ac.kgu.app.trail.ui.base.viewBinding


class AddressBottomSheetFragment(context: Context, addressEntryList : List<String>) : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentAddressBottomSheetBinding::bind)
    private lateinit var onClickListener: onBottomSheetClickListener

    private val adapter = AddressAdapter(context,addressEntryList)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_address_bottom_sheet, container, false)

    override fun getTheme(): Int {
        return R.style.BottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addressBottomSheetListView.adapter = adapter
        binding.addressBottomSheetListView.setOnItemClickListener { _, _, position, _ ->
            onClickListener.onClick(adapter.getItem(position).toString())
            dismiss()
        }

        // 팝업 생성 시 전체화면으로 띄우기
        val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        val behavior = BottomSheetBehavior.from<View>(bottomSheet!!)
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED

        // 드래그해도 팝업이 종료되지 않도록
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })



    }
    fun setOnClickListener(listener: onBottomSheetClickListener) {
        onClickListener = listener
    }

    interface onBottomSheetClickListener{
        fun onClick(clickItem: String)
    }
}
