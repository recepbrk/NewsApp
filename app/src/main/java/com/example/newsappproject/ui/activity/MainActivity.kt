package com.example.newsappproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newsappproject.R
import com.example.newsappproject.databinding.ActivityMainBinding
import com.example.newsappproject.ui.category.CategoryFragment
import com.example.newsappproject.ui.home.HomeFragment
import com.example.newsappproject.ui.profile.ProfileFragment
import com.example.newsappproject.ui.save.SaveFragment
import com.example.newsappproject.ui.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_home ->{
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.bottom_category ->{
                    replaceFragment(CategoryFragment())
                    true
                }

                R.id.bottom_save ->{
                    replaceFragment(SaveFragment())
                    true
                }

                R.id.bottom_search ->{
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.bottom_profile ->{
                    replaceFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
        replaceFragment(HomeFragment())
    }


    private fun replaceFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container,fragment).commit()
    }
}