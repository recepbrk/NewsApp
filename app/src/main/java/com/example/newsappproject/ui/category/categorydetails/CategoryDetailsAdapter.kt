package com.example.newsappproject.ui.category.categorydetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsappproject.R
import com.example.newsappproject.data.model.ResponseTopHeadLine

class CategoryDetailsAdapter : RecyclerView.Adapter<CategoryDetailsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val callback = object : DiffUtil.ItemCallback<ResponseTopHeadLine.Article>() {
        override fun areItemsTheSame(
            oldItem: ResponseTopHeadLine.Article,
            newItem: ResponseTopHeadLine.Article
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: ResponseTopHeadLine.Article,
            newItem: ResponseTopHeadLine.Article
        ): Boolean {
            return oldItem == newItem
        }

    }
    val asynListDiffer = AsyncListDiffer(this@CategoryDetailsAdapter, callback)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryDetailsAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        )
    }

    override fun getItemCount(): Int = asynListDiffer.currentList.size

    override fun onBindViewHolder(holder: CategoryDetailsAdapter.ViewHolder, position: Int) {

        val list = asynListDiffer.currentList[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.tvTitle).text = list.title
            findViewById<TextView>(R.id.tvNewsDetails).text = list.description
            findViewById<TextView>(R.id.tvReference).text = list.source?.name
            findViewById<TextView>(R.id.tvPublishedDate).text = list.publishedAt
            Glide.with(this).load(list.urlToImage).into(findViewById(R.id.imgNews))

            setOnClickListener {
                OnItemClickListener?.let { listener ->
                    listener(list)

                }
            }
        }
    }

    private var OnItemClickListener: ((ResponseTopHeadLine.Article) -> Unit)? = null
    fun setOnItemClickListener(listener: (ResponseTopHeadLine.Article) -> Unit) {
        OnItemClickListener = listener
    }

}