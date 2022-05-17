package kr.ac.kgu.app.trail.ui.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.databinding.FragmentHistoryBinding
import net.daum.mf.map.api.MapView

class HistoryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)


        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_appbar_menu, menu)

        return super.onCreateOptionsMenu(menu, inflater)
    }
}