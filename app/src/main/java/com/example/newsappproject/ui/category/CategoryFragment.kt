package com.example.newsappproject.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsappproject.R
import com.example.newsappproject.databinding.FragmentCategoryBinding
import com.example.newsappproject.ui.category.adapter.CategoryAdapter

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryArrayList: ArrayList<CategoryItem>
    lateinit var image: Array<Int>
    lateinit var title: Array<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image = arrayOf(
            R.drawable.hot_news,
            R.drawable.technology,
            R.drawable.sports,
            R.drawable.finance,
            R.drawable.science,
            R.drawable.cinema,
            R.drawable.life,
            R.drawable.game,
            R.drawable.politics,
            R.drawable.fashion,
            R.drawable.culture
        )


        title = arrayOf(
            "HOT NEWS",
            "TECHNOLOGY",
            "SPORT",
            "FINANCE",
            "SCIENCE",
            "CINEMA & TV",
            "LIFE",
            "GAME",
            "POLITICS",
            "FASHION & BEAUTY",
            "CULTURE & ART"

        )

        binding.categoryRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (position) {
                        0 -> 2
                        else -> 1
                    }
                }

            }
        }
        binding.categoryRecyclerView.setHasFixedSize(true)


        categoryArrayList = arrayListOf<CategoryItem>()
        getData()


    }

    private fun getData() {
        for (i in image.indices) {
            val category = CategoryItem(image[i], title[i])
            categoryArrayList.add(category)
        }
        binding.categoryRecyclerView.adapter = CategoryAdapter(categoryArrayList)
    }


}