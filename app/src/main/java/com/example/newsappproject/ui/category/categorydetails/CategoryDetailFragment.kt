package com.example.newsappproject.ui.category.categorydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsappproject.databinding.FragmentCategoryDetailBinding
import com.example.newsappproject.util.resource.DataStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryDetailFragment : Fragment() {
    private lateinit var binding: FragmentCategoryDetailBinding
    private val categoryDetailsViewModel: CategoryDetailsViewModel by viewModels()
    private lateinit var categoryDetailAdapter: CategoryDetailsAdapter//Burada yeniden adapter yazmama gerek yoktu SearchAdapteri kullanabilirdim.
    private val args: CategoryDetailFragmentArgs by navArgs()
    private var categoryTitle = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryDetailToolbarTitle.text = args.argCategoryTitle
        binding.backIcon.setOnClickListener {
            val action =
                CategoryDetailFragmentDirections.actionCategoryDetailFragmentToBottomCategory()
            findNavController().navigate(action)
        }
        setupRecyclerview()
        searchQuery()
        observeData()
    }


    private fun setupRecyclerview() {
        categoryDetailAdapter = CategoryDetailsAdapter()
        binding.categoryDetailsRecyclerView.adapter = categoryDetailAdapter
        categoryDetailAdapter.setOnItemClickListener {
            val action =
                CategoryDetailFragmentDirections.actionCategoryDetailFragmentToDetailsFragment(it)
            findNavController().navigate(action)

        }
    }

    private fun searchQuery() {

        var job: Job? = null
        job?.cancel()
        job = MainScope().launch {
            delay(500L)

            categoryDetailsViewModel.getNewsEverything(args.argCategoryTitle)
        }
    }


    private fun observeData() {
        categoryDetailsViewModel.searchNews.observe(viewLifecycleOwner) {
            when (it.status) {
                DataStatus.Status.SUCCESS -> {
                    it.data?.let {
                        categoryDetailAdapter.asynListDiffer.submitList(it.articles)
                    }
                }

                else -> {
                    it.message?.let { message ->
                        Toast.makeText(context, "arama yapılmadı", Toast.LENGTH_LONG).show()

                    }
                }

            }
        }
    }
}