package com.example.newsappproject.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappproject.databinding.FragmentHomeBinding
import com.example.newsappproject.ui.adapter.NewsAdapter
import com.example.newsappproject.util.extensions.initRecycler
import com.example.newsappproject.util.extensions.isVisible
import com.example.newsappproject.util.resource.DataStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var newsAdapter: NewsAdapter
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        lifecycleScope.launch {
            binding.apply {
                viewModel.getTopHeadlineNews("us")
                viewModel.newsList.observe(viewLifecycleOwner) {
                    when (it.status) {
                        DataStatus.Status.LOADİNG -> {
                            pbLoading.isVisible(true, rvLastNews)
                        }

                        DataStatus.Status.SUCCESS -> {
                            pbLoading.isVisible(false, rvLastNews)
                            newsAdapter.setData(it.data!!)
                            newsAdapter.setOnItemClickListener {

                                val direction =
                                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it.url)
                                findNavController().navigate(direction)
                                Toast.makeText(context, "click çalıştı", Toast.LENGTH_LONG).show()
                            }


                        }

                        DataStatus.Status.ERROR -> {
                            Toast.makeText(
                                requireContext(),
                                "Something went wrong!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }


    private fun setupRecyclerView() {
        binding.rvLastNews.initRecycler(LinearLayoutManager(requireActivity()), newsAdapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}