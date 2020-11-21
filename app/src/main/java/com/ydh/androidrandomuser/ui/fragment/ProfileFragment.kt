package com.ydh.androidrandomuser.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.ydh.androidrandomuser.R
import com.ydh.androidrandomuser.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        Glide.with(binding.root).load("https://www.linkpicture.com/q/IMG_20200523_155440-1kk.jpg").circleCrop().into(binding.imageView)
        return binding.root
    }

}