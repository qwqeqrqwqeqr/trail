package kr.ac.kgu.app.trail.ui.login.address.bottomsheet

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kr.ac.kgu.app.trail.R
import kr.ac.kgu.app.trail.databinding.AddressListItemBinding
import kr.ac.kgu.app.trail.ui.course.CourseAdapter

class AddressAdapter(private val context: Context, private val entries: List<String>): BaseAdapter() {
    override fun getCount(): Int =entries.size

    override fun getItem(position: Int): String = entries[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = AddressListItemBinding.inflate(LayoutInflater.from(context),parent,false)

        var entry : String = entries[position]
        binding.addressListItemText.text = entry

        return binding.root
    }
}