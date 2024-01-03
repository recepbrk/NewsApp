package com.example.newsappproject.ui.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsappproject.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nightModeCheck()
        Handler(Looper.getMainLooper()).postDelayed({

            if (onBoardingFinished()) {
                view?.post {
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }
            } else {
                view?.post {
                    findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
                }
            }

        }, 3000)
    }

    private fun onBoardingFinished(): Boolean {

        val sharedPref = requireContext().getSharedPreferences(
            "onboarding",
            Context.MODE_PRIVATE
        )
        return sharedPref.getBoolean("finished", false)
    }

    private fun nightModeCheck() {

        val sharedPreferences =
            requireContext().getSharedPreferences("Mode", Context.MODE_PRIVATE)
        val nightMode = sharedPreferences.getBoolean("night", false)

        if (nightMode == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        }
    }


}