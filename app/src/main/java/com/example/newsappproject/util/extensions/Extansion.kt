package com.example.newsappproject.util.extensions

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun View.isVisible(isShowLoading: Boolean, container: View) {
    if (isShowLoading) {
        this.visibility = View.VISIBLE
        container.visibility = View.GONE
    } else {
        this.visibility = View.GONE
        container.visibility = View.VISIBLE
    }
}

fun RecyclerView.initRecycler(
    layoutManager: RecyclerView.LayoutManager,
    adapter: RecyclerView.Adapter<*>
) {
    this.adapter = adapter
    this.layoutManager = layoutManager
}
