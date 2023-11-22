package com.example.newsappproject.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.newsappproject.data.model.ResponseTopHeadLine

class NewsDiffUtil(
    private val oldlist: List<ResponseTopHeadLine.Article>,
    private val newlist: List<ResponseTopHeadLine.Article>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldlist.size

    override fun getNewListSize(): Int = newlist.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldlist[oldItemPosition] === newlist[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldlist[oldItemPosition] === newlist[newItemPosition]
    }
}