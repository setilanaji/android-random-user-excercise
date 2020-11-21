package com.ydh.androidrandomuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.ydh.androidrandomuser.R
import com.ydh.androidrandomuser.viewmodel.UserViewModelFactory
import com.ydh.androidrandomuser.viewmodel.UsersViewModel
import com.ydh.androidrandomuser.databinding.FragmentUsersBinding
import com.ydh.androidrandomuser.model.UserModel

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
            generateProduct(it)

        })
    }

    private fun generateProduct(it: List<UserModel>) {
        val list = mutableListOf<User>()
        var temp = ""

        it.forEach { model ->
            if (temp != model.name.first[0].toUpperCase().toString()) {
                temp = model.name.first[0].toUpperCase().toString()

                list.add( User.Category(temp))
            }

            list.add(User.Data(model))
        }
        userAdapter?.setData(list)
//        adapter.list = list
    }

//    private fun setAdapter(){
////        userAdapter = UserAdapter(requireContext())
//    }

}