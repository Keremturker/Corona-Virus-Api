package com.keremturker.coronavirusapi.ui.fragment.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keremturker.coronavirusapi.databinding.CountriesLayoutItemBinding
import com.keremturker.coronavirusapi.repository.entity.response.CountriesResponseItem

class CountriesListAdapter(private val onClickAction: (CountriesResponseItem) -> Unit) :
    RecyclerView.Adapter<CountriesListAdapter.CountriesHolder>() {

    private val data: MutableList<CountriesResponseItem> = ArrayList()

    class CountriesHolder(
        private val itemBinding: CountriesLayoutItemBinding,
        private val onClickAction: (CountriesResponseItem) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: CountriesResponseItem) {
            itemBinding.txtCountryName.text = item.country

            itemBinding.root.setOnClickListener {
                onClickAction.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountriesHolder {
        val itemBinding =
            CountriesLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountriesHolder(itemBinding, onClickAction)
    }

    override fun onBindViewHolder(holder: CountriesHolder, position: Int) {
        val item: CountriesResponseItem = data[position] ?: return
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newData: List<CountriesResponseItem>?) {
        data.clear()
        notifyDataSetChanged()

        if (newData == null) {
            return
        }

        data.addAll(newData)
        notifyDataSetChanged()
    }
}