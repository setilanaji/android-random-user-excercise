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
    data class MapList(val name: String, var index: Int)

    private fun generateProduct(it: List<UserModel>) {
        val list = mutableListOf<User>()
        var temp = ""
        var listCategory = mutableListOf<String>()
        var listf: List<String>
        var index = 0
        var countPos = 0
        var after = 0
        var listContainer = mutableListOf<MapList>()
        it.forEach { model ->
//            println("now $now")
            if (temp != model.name.first[0].toUpperCase().toString()) {
                temp = model.name.first[0].toUpperCase().toString()

                if (listCategory.isEmpty()){
                    listCategory.add(temp)
                    listf = listCategory.sorted()

                    listContainer.add(index, MapList(temp, countPos))
                    println("rencana $temp = $countPos")

                    list.add(listContainer[index].index, User.Category(temp))
//                    println(listContainer[index].name)
//                    countPos += 1
                }else{
                    if ((listCategory.find { x -> x == temp }).isNullOrEmpty()){
                        listCategory.add(temp)
                        listf = listCategory.sorted()
                        println(listf)
                        for ((i, item) in listf.withIndex()){
                            if (item == temp){
                                index = i
                                countPos = if (i != 0){
                                    listContainer[i-1].index
                                }else{
                                    listContainer[i].index
                                }
                                after = countPos + 1

                                println("rencana $temp = $countPos")
                                listContainer.add(index, MapList(temp, countPos))
                                for(k in (after) until listContainer.size){
                                    listContainer[k].index += 1
                                }
                                list.add(listContainer[index].index, User.Category(temp))

//                                countPos += 1
//                                for (x in listContainer){
//                                    if (x.name == temp){
//                                        list.add(x.index, User.Category(temp))
////                                        x.index = countPos
//                                    }
//                                }



//                                println(listContainer[index].index)
                            }
                        }

                    }
                }
            }
//            list.add(listContainer[index].index , User.Data(model))
//            now += 1
//            listContainer[index].index = now
            listContainer.forEach{
                println("${it.name} ${it.index}")
            }

        }
        userAdapter?.setData(list)
//        adapter.list = list
    }

//    private fun setAdapter(){
////        userAdapter = UserAdapter(requireContext())
//    }

}