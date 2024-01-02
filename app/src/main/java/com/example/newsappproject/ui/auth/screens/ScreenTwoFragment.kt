package com.example.newsappproject.ui.auth.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.newsappproject.R
import com.example.newsappproject.databinding.FragmentScreenTwoBinding


class ScreenTwoFragment : Fragment() {
    private lateinit var binding: FragmentScreenTwoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreenTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.nextButton2.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

            viewPager?.currentItem = 2
        }
    }
}