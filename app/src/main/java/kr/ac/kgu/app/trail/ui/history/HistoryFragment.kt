package kr.ac.kgu.app.trail.ui.history

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.data.model.HistoryEntry
import kr.ac.kgu.app.trail.databinding.FragmentCourseBinding
import kr.ac.kgu.app.trail.databinding.FragmentHistoryBinding
import kr.ac.kgu.app.trail.ui.base.BaseFragment
import kr.ac.kgu.app.trail.ui.base.InitView
import kr.ac.kgu.app.trail.ui.base.viewBinding
import kr.ac.kgu.app.trail.ui.course.CourseAdapter
import kr.ac.kgu.app.trail.ui.course.CourseViewModel
import kr.ac.kgu.app.trail.util.DataState
import net.daum.mf.map.api.MapView

@AndroidEntryPoint
class HistoryFragment : BaseFragment<HistoryViewModel, DataState<List<HistoryEntry>>>(
    R.layout.fragment_history,
    HistoryViewModel::class.java
), InitView {

    private val binding by viewBinding(FragmentHistoryBinding::bind)
    private lateinit var historyAdapter: HistoryAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initListeners()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_appbar_menu, menu)

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun updateUi(model: DataState<List<HistoryEntry>>) {
        when (model) {
            is DataState.Success -> {
                historyAdapter.submitList(model.data.toMutableList())
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

    override fun initUi() {
        historyAdapter = HistoryAdapter(requireContext())
        binding.historyRecyclerView.apply {
            adapter = historyAdapter
        }
    }

    override fun initListeners() {

    }
}