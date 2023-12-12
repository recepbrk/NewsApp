package com.example.newsappproject.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappproject.R
import com.example.newsappproject.ui.category.CategoryItem

class CategoryAdapter(
    private val categoryList: ArrayList<CategoryItem>,
    val listener: MyClickListener
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                listener.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = categoryList[position]
        holder.itemView.apply {
            findViewById<ImageView>(R.id.category_imageView).setImageResource(currentItem.image)
            findViewById<TextView>(R.id.category_textView).text = currentItem.title


        }
    }

    interface MyClickListener {
        fun onClick(position: Int)
    }
}





