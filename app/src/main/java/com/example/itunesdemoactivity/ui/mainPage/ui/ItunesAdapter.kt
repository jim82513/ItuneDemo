package com.example.itunesdemoactivity.ui.mainPage.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesdemoactivity.data.ItunesInfo
import com.example.itunesdemoactivity.databinding.SearchInfoViewholderBinding

class ItunesAdapter(private val _listener: IClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var infoList: List<ItunesInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun interface IClickListener{
        fun onClick(msg: String)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SearchInfoViewHolder(SearchInfoViewholderBinding.inflate(layoutInflater, parent, false), _listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchInfoViewHolder).setDataInfo(infoList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return infoList.size
    }
}