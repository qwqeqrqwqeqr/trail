package kr.ac.kgu.app.trail.ui.account

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import kr.ac.kgu.app.trail.R

class AccountFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.account_appbar_menu, menu)

        return super.onCreateOptionsMenu(menu, inflater)
    }

}