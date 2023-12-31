package com.example.newsappproject.ui.auth.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsappproject.R
import com.example.newsappproject.databinding.FragmentOnBoardingBinding
import com.example.newsappproject.ui.auth.onboarding.screens.ScreenOneFragment
import com.example.newsappproject.ui.auth.onboarding.screens.ScreenThreeFragment
import com.example.newsappproject.ui.auth.onboarding.screens.ScreenTwoFragment
import com.example.newsappproject.ui.auth.onboarding.viewpager.ViewPagerAdapter

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fragmentList = arrayListOf<Fragment>(
            ScreenOneFragment(),
            ScreenTwoFragment(),
            ScreenThreeFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            childFragmentManager,
            lifecycle

        )

        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)

    }


}