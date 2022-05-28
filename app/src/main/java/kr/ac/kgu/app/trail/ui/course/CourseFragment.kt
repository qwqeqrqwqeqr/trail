package kr.ac.kgu.app.trail.ui.course

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.data.model.Constants.COURSE_ENTRY
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.databinding.FragmentCourseBinding
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
        courseAdapter.setItemClickListener { showConfirmationDialog(it) }
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


    private fun showConfirmationDialog(courseEntry: CourseEntry) {
        MaterialAlertDialogBuilder(requireContext(), R.style.MaterialAlertDialogStyle)
            .setTitle(getString(R.string.course_dialog_title))
            .setMessage(setCourseConfirmationDialogDescription(courseEntry))
            .setPositiveButton(getString(R.string.course_dialog_yes)) { _, _ ->
                navigateToRace(courseEntry)
            }
            .setNegativeButton(getString(R.string.course_dialog_cancel)) { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun setCourseConfirmationDialogDescription(courseEntry: CourseEntry): String =
        "\n" + resources.getString(R.string.course_list_item_name_text) + ": \t" + courseEntry.courseName + "\n" +
                resources.getString(R.string.course_list_item_address_text) + ": \t" + courseEntry.courseAddress + "\n" +
                resources.getString(R.string.course_list_item_distance_text) + ": \t" + courseEntry.courseDistance + "\n" +
                resources.getString(R.string.course_list_item_time_text) + ": \t" + courseEntry.time + "\n"

    private fun navigateToRace(courseEntry: CourseEntry) {
        val intent = Intent(requireContext(), RaceActivity::class.java)
        intent.putExtra(COURSE_ENTRY, courseEntry)
        startActivity(intent)
    }


}