package com.example.newsappproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappproject.data.model.ResponseTopHeadLine
import com.example.newsappproject.databinding.NewsItemBinding
import javax.inject.Inject

class NewsAdapter @Inject constructor() : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private var news = emptyList<ResponseTopHeadLine.Article>()
    lateinit var binding: NewsItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = NewsItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(news[position])
    }

    inner class MyViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ResponseTopHeadLine.Article) {
            binding.article = article
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }

        }
    }

    private var onItemClickListener: ((ResponseTopHeadLine.Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (ResponseTopHeadLine.Article) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(newData: ResponseTopHeadLine) {
        val newsDiffUtil = NewsDiffUtil(news, newData.articles)
        val diffUtils = DiffUtil.calculateDiff(newsDiffUtil)
        news = newData.articles
        diffUtils.dispatchUpdatesTo(this)
    }

}