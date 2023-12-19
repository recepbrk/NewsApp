package com.example.newsappproject.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsappproject.databinding.FragmentSearchBinding
import com.example.newsappproject.ui.search.adapter.SearchAdapter
import com.example.newsappproject.util.resource.DataStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerview()
        searchQuery()
        observeData()
    }

    private fun setupRecyclerview() {
        searchAdapter = SearchAdapter()
        binding.searchRecyclerView.adapter = searchAdapter
        searchAdapter.setOnItemClickListener {
            val action = SearchFragmentDirections.actionBottomSearchToDetailsFragment(it.url)
            findNavController().navigate(action)

        }
    }

    private fun searchQuery() {

        var job: Job? = null
        binding.searchEditText.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable.let {
                    if (editable.toString().isNotEmpty()) {
                        searchViewModel.getNewsEverything(editable.toString())
                    }
                }
            }
        }
    }

    private fun observeData() {
        searchViewModel.searchNews.observe(viewLifecycleOwner) {
            when (it.status) {
                DataStatus.Status.SUCCESS -> {
                    it.data?.let {
                        searchAdapter.asynListDiffer.submitList(it.articles)
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