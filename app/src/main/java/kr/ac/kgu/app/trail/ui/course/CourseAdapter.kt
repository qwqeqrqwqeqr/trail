package kr.ac.kgu.app.trail.ui.course

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kgu.app.trail.R
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

        holder.binding.courseListItemLevelText.setTextColor(
            ContextCompat.getColor(
                context,
                if (courseEntry.level=="어려움") R.color.hard_level_color else if(courseEntry.level=="보통") R.color.normal_level_color else R.color.easy_level_color
            )
        )

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { click ->
                click(courseEntry)
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