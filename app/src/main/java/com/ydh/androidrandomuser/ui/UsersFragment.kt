package com.ydh.androidrandomuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.ydh.androidrandomuser.R
import com.ydh.androidrandomuser.UserAdapter
import com.ydh.androidrandomuser.UserViewModelFactory
import com.ydh.androidrandomuser.UsersViewModel
import com.ydh.androidrandomuser.databinding.FragmentUsersBinding

class UsersFragment : Fragment() {

    lateinit var binding: FragmentUsersBinding
    private val userAdapter by lazy {
        context?.let { UserAdapter(it) }
    }
    lateinit var userViewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users,
            container, false)

//        setAdapter()
        setViewModel()
        setData()
        setObserver()

        binding.rvUsers.adapter = userAdapter
        return binding.root
    }

    private fun setViewModel(){
        userViewModel = ViewModelProviders.of(this, UserViewModelFactory(this.context))
            .get(UsersViewModel::class.java)
    }

    private fun setData(){
        userViewModel.setAllUser()
    }

    private fun setObserver(){
        userViewModel.users.observe(viewLifecycleOwner, {
            userAdapter?.setData(it)
        })
    }

//    private fun setAdapter(){
////        userAdapter = UserAdapter(requireContext())
//    }

}