package com.ydh.androidrandomuser.ui.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ydh.androidrandomuser.R
import com.ydh.androidrandomuser.viewmodel.UserViewModelFactory
import com.ydh.androidrandomuser.viewmodel.UsersViewModel
import com.ydh.androidrandomuser.databinding.FragmentUsersBinding
import com.ydh.androidrandomuser.model.User
import com.ydh.androidrandomuser.model.UserModel
import com.ydh.androidrandomuser.util.SwipeToDelete
import com.ydh.androidrandomuser.ui.UserAdapter

class UsersFragment : Fragment() {

    lateinit var binding: FragmentUsersBinding
    private val userAdapter by lazy {
        context?.let { UserAdapter(it) }
    }
    private lateinit var userViewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_users,
            container, false
        )

        setViewModel()
        setData()
        setObserver()

        binding.run {
            rvUsers.adapter = userAdapter
            rvUsers.addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )

            val swipeHandler = object : SwipeToDelete(requireContext()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = rvUsers.adapter as UserAdapter
                    val pos = viewHolder.adapterPosition
                    val item = adapter.getData(pos)
                    adapter.removeAt(pos)

                    val snack =
                        view?.let {
                            Snackbar.make(
                                it,
                                "Item was removed from the list.",
                                Snackbar.LENGTH_LONG
                            )
                        }
                    snack?.setAction("UNDO") {
                        adapter.restoreItem(item, pos)
                        rvUsers.scrollToPosition(pos)
                    }

                    snack?.setActionTextColor(Color.BLUE)
                    snack?.show()
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(rvUsers)
        }

        return binding.root

    }


    private fun setViewModel() {
        userViewModel = ViewModelProviders.of(this, UserViewModelFactory(this.context))
            .get(UsersViewModel::class.java)
    }

    private fun setData() {
        userViewModel.setAllUser()
    }

    private fun setObserver() {
        userViewModel.users.observe(viewLifecycleOwner, {
            generateProduct(it)

        })
    }

    private fun generateProduct(it: List<UserModel>) {
        val list = mutableListOf<User>()
        val sortedList = it.sortedBy { it.name.first }
        var temp = ""

        sortedList.forEach { model ->
            if (temp != model.name.first[0].toUpperCase().toString()) {
                temp = model.name.first[0].toUpperCase().toString()
                list.add(User.Category(temp))
            }
            list.add(User.Data(model))
        }
        userAdapter?.setData(list)
    }


}