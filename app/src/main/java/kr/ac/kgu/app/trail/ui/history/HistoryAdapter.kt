package kr.ac.kgu.app.trail.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kgu.app.trail.data.model.HistoryEntry
import kr.ac.kgu.app.trail.databinding.HistoryListItemBinding

class HistoryAdapter(private val context: Context):   RecyclerView.Adapter<HistoryAdapter.EntriesViewHolder>() {

    private var entries = mutableListOf<HistoryEntry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntriesViewHolder {
        return EntriesViewHolder(HistoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EntriesViewHolder, position: Int) {
        val historyEntry = getItem(position)

        holder.binding.historyListItemCouseNameText.text = historyEntry.courseName
        holder.binding.historyListItemCourseAddressText.text = historyEntry.courseAddress
        holder.binding.historyListItemDistanceText.text = historyEntry.distance.toString()
        holder.binding.historyListItemWorkStartTimeText.text = historyEntry.workStartTime
    }



    override fun getItemCount(): Int = entries.count()
    fun getItem(position: Int): HistoryEntry = entries[position]

    inner class EntriesViewHolder(val binding: HistoryListItemBinding) :  RecyclerView.ViewHolder(binding.root)


    fun submitList(entries: MutableList<HistoryEntry>) {
        this.entries.clear()
        this.entries = entries
        notifyDataSetChanged()
    }


}