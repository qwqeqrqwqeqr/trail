package kr.ac.kgu.app.trail.ui.course

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R

import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.databinding.FragmentCourseBinding
import kr.ac.kgu.app.trail.ui.auth.LoginActivity
import kr.ac.kgu.app.trail.ui.base.BaseFragment
import kr.ac.kgu.app.trail.ui.base.viewBinding
import kr.ac.kgu.app.trail.ui.race.RaceActivity
import kr.ac.kgu.app.trail.util.DataState

@AndroidEntryPoint
class CourseFragment : BaseFragment<CourseViewModel, DataState<List<CourseEntry>>>(
    R.layout.fragment_course,
    CourseViewModel::class.java
) {

    private val binding by viewBinding(FragmentCourseBinding::bind)
    private lateinit var courseAdapter: CourseAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseAdapter = CourseAdapter(requireContext())
        binding.courseRecyclerView.apply {
            adapter = courseAdapter
              }

        courseAdapter.setItemClickListener {
            //Todo  Naigate RaceActivity
        }
    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.course_appbar_menu, menu)

        return super.onCreateOptionsMenu(menu, inflater)
    }


    override fun updateUi(model: DataState<List<CourseEntry>>) {
        when (model) {
            is DataState.Success -> {
                courseAdapter.submitList(model.data.toMutableList())
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



}