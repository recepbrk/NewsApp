package com.example.newsappproject.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappproject.R
import com.example.newsappproject.databinding.FragmentSaveBinding
import com.example.newsappproject.ui.search.adapter.SearchAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveFragment : Fragment() {
    private lateinit var binding: FragmentSaveBinding
    private val favViewModel: SaveViewModel by viewModels()
    lateinit var favAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        createRecyclerView()
        observeData()
        deleteProcess()
    }

    private fun createRecyclerView() {
        favAdapter = SearchAdapter()
        binding.recyclerviewFavorite.adapter = favAdapter
        favAdapter.setOnItemClickListener {
            val action =
                SaveFragmentDirections.actionBottomSaveToDetailsFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun observeData() {
        favViewModel.getFavArticles().observe(viewLifecycleOwner) {
            favAdapter.asynListDiffer.submitList(it)
        }
    }

    private fun deleteProcess() {
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = favAdapter.asynListDiffer.currentList[position]
                favViewModel.deleteArticle(article)
                Snackbar.make(
                    requireView(),
                    getString(R.string.successfully_deleted_message),
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setAction(getString(R.string.snackbar_undo)) {
                        favViewModel.addArticle(article)
                    }.show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(binding.recyclerviewFavorite)
        }
    }


}