package kr.ac.kgu.app.trail.ui.course

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kgu.app.trail.data.model.CourseEntry
import kr.ac.kgu.app.trail.databinding.CourseListItemBinding

class CourseAdapter(private val context: Context):   RecyclerView.Adapter<CourseAdapter.EntriesViewHolder>() {

    private var entries = mutableListOf<CourseEntry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntriesViewHolder {
        return EntriesViewHolder(CourseListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EntriesViewHolder, position: Int) {
        val courseEntry = getItem(position)

        holder.binding.courseListItemCouseNameText.text = courseEntry.courseName
        holder.binding.courseListItemCourseAddressText.text = courseEntry.courseAddress
        holder.binding.courseListItemDistanceText.text = courseEntry.courseDistance
        holder.binding.courseListItemLevelText.text = courseEntry.level
        holder.binding.courseListItemTimeText.text = courseEntry.time



        holder.itemView.setOnClickListener {
            onItemClickListener?.let { click ->
//                if (position == itemCount - 1) courseEntry.isInitialEntry = true
//                click(weightEntry)
            }
        }
    }



    override fun getItemCount(): Int = entries.count()
    fun getItem(position: Int): CourseEntry = entries[position]

    inner class EntriesViewHolder(val binding: CourseListItemBinding) :  RecyclerView.ViewHolder(binding.root)


    fun submitList(entries: MutableList<CourseEntry>) {
        this.entries.clear()
        this.entries = entries
        notifyDataSetChanged()
    }

    var onItemClickListener: ((CourseEntry) -> Unit)? = null

    fun setItemClickListener(listener: ((CourseEntry) -> Unit)?) {
        onItemClickListener = listener
    }

}