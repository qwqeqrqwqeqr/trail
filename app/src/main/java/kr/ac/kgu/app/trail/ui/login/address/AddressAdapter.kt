package kr.ac.kgu.app.trail.ui.login.address

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kgu.app.trail.data.model.AddressEntry
import kr.ac.kgu.app.trail.databinding.AddressListItemBinding

class AddressAdapter(private val context: Context) :
    RecyclerView.Adapter<AddressAdapter.EntriesViewHolder>() {

    private var entries = mutableListOf<AddressEntry>()

    inner class EntriesViewHolder(val binding: AddressListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntriesViewHolder {
        return EntriesViewHolder(
            AddressListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


    }

    override fun onBindViewHolder(holder: EntriesViewHolder, position: Int) {
        val weightEntry = getItem(position)


//        holder.binding.currentWeight.text =
//            context.getString(R.string.kg, weightEntry.currentWeight.toString())
//        holder.binding.date.text = weightEntry.date
    }

    override fun getItemCount(): Int= entries.count()
    fun getItem(position: Int): AddressEntry = entries[position]

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(entries: MutableList<AddressEntry>) {
        this.entries.clear()
        this.entries = entries
        notifyDataSetChanged()
    }


}
