package com.example.itunesdemoactivity.ui.mainPage.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.itunesdemoactivity.R
import com.example.itunesdemoactivity.data.ItunesInfo
import com.example.itunesdemoactivity.databinding.SearchInfoViewholderBinding

class SearchInfoViewHolder(private val mBinding: SearchInfoViewholderBinding, private val clickListener: ItunesAdapter.IClickListener): RecyclerView.ViewHolder(mBinding.root) {

    fun setDataInfo(itunesData: ItunesInfo) {
        mBinding.data = itunesData
        mBinding.artistName.text = itunesData.artistName
        mBinding.trackName.text = itunesData.trackName
        Glide.with(itemView.context).load(itunesData.trackViewUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
            )
            .into(mBinding.trackImage)
        setClickButtonListener(itunesData)
    }

    private fun setClickButtonListener(itunesData: ItunesInfo) {
        mBinding.infoLayout.setOnClickListener{
            clickListener.onClick(itunesData.previewUrl)
        }
    }
}