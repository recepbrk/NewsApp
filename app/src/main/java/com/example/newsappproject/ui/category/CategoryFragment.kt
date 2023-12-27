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
        categoryArrayList.add(CategoryItem(R.drawable.hot_news, getString(R.string.hot_news)))
        categoryArrayList.add(CategoryItem(R.drawable.technology, getString(R.string.technology)))
        categoryArrayList.add(CategoryItem(R.drawable.sports, getString(R.string.sport)))
        categoryArrayList.add(CategoryItem(R.drawable.finance, getString(R.string.finance)))
        categoryArrayList.add(CategoryItem(R.drawable.science, getString(R.string.science)))
        categoryArrayList.add(CategoryItem(R.drawable.cinema, getString(R.string.cinema)))
        categoryArrayList.add(CategoryItem(R.drawable.life, getString(R.string.life)))
        categoryArrayList.add(CategoryItem(R.drawable.game, getString(R.string.game)))
        categoryArrayList.add(CategoryItem(R.drawable.politics, getString(R.string.politics)))
        categoryArrayList.add(CategoryItem(R.drawable.fashion, getString(R.string.fashion)))
        categoryArrayList.add(CategoryItem(R.drawable.culture, getString(R.string.culture)))

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