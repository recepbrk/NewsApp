package com.example.newsappproject.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsappproject.R
import com.example.newsappproject.databinding.FragmentCategoryBinding
import com.example.newsappproject.ui.category.adapter.CategoryAdapter

class CategoryFragment : Fragment(), CategoryAdapter.MyClickListener {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryadapter: CategoryAdapter
    private lateinit var categoryArrayList: ArrayList<CategoryItem>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryArrayList = ArrayList()
        categoryArrayList.add(CategoryItem(R.drawable.hot_news, "HOT NEWS"))
        categoryArrayList.add(CategoryItem(R.drawable.technology, "TECHNOLOGY"))
        categoryArrayList.add(CategoryItem(R.drawable.sports, "SPORT"))
        categoryArrayList.add(CategoryItem(R.drawable.finance, "FINANCE"))
        categoryArrayList.add(CategoryItem(R.drawable.science, "SCIENCE"))
        categoryArrayList.add(CategoryItem(R.drawable.cinema, "CINEMA & TV"))
        categoryArrayList.add(CategoryItem(R.drawable.life, "LIFE"))
        categoryArrayList.add(CategoryItem(R.drawable.game, "GAME"))
        categoryArrayList.add(CategoryItem(R.drawable.politics, "POLITICS"))
        categoryArrayList.add(CategoryItem(R.drawable.fashion, "FASHION & BEAUTY"))
        categoryArrayList.add(CategoryItem(R.drawable.culture, "CULTURE & ART"))

        categoryadapter = CategoryAdapter(categoryArrayList, this@CategoryFragment)
        binding.categoryRecyclerView.adapter = categoryadapter
        binding.categoryRecyclerView.setHasFixedSize(true)
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


    }

    override fun onClick(position: Int) {
        val action =
            CategoryFragmentDirections.actionBottomCategoryToCategoryDetailFragment(
                categoryArrayList[position].title
            )

        findNavController().navigate(action)


    }


}