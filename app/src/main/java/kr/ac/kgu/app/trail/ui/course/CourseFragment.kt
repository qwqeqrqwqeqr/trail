package kr.ac.kgu.app.trail.ui.course

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.kgu.app.trail.R

import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.ui.base.BaseFragment
import kr.ac.kgu.app.trail.util.DataState

@AndroidEntryPoint
class CourseFragment : BaseFragment<CourseViewModel, DataState<List<CourseEntry>>>(
    R.layout.fragment_course,
    CourseViewModel::class.java
) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)


        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.course_appbar_menu, menu)

        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun updateUi(model: DataState<List<CourseEntry>>) {
        TODO("Not yet implemented")
    }

}