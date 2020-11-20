package com.ydh.androidrandomuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.ydh.androidrandomuser.R
import com.ydh.androidrandomuser.databinding.FragmentMainBinding
import com.ydh.androidrandomuser.model.Page

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.run {
            val pages = listOf(Page("Users", UsersFragment()), Page("Profile", ProfileFragment()))
            val adapter = ViewPagerAdapter(pages, childFragmentManager, lifecycle)

            viewpager.adapter = adapter
            TabLayoutMediator(tabs, viewpager) { tab, i ->
                tab.text = pages[i].title
            }.attach()
        }
        return binding.root
    }


}

