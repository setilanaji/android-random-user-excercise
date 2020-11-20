package com.ydh.androidrandomuser.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ydh.androidrandomuser.R
import com.ydh.androidrandomuser.databinding.ItemUserBinding
import com.ydh.androidrandomuser.databinding.ItemUserCategoryTitleBinding
import com.ydh.androidrandomuser.model.UserModel
import java.util.*

sealed class User{
    data class Category(val category: String):User()
    data class Data(val user: UserModel): User()
}

class UserAdapter(
    private val context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var userList = listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            TYPE_HEADER -> HeaderViewHolder(
                ItemUserCategoryTitleBinding.inflate(LayoutInflater.from(context), parent, false)
            )
            TYPE_DATA -> UserViewHolder(
                ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
            )
            else -> throw IllegalArgumentException("Unsupported view type")
        }

//        val inflater = LayoutInflater.from(context)
//        val binding: ItemUserBinding =
//            DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false)
//
//        return UserViewHolder(binding)
    }

//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
////        holder.userBinding.user = userList[position]
//    }

    fun setData(item: List<User>){
        this.userList = item
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UserViewHolder(
        private val userBinding: ItemUserBinding
    ) : RecyclerView.ViewHolder(userBinding.root) {
        private var binding: ItemUserBinding? = null

//        init {
//            this.binding = userBinding
//        }

        fun bindData(user: UserModel){
            this.userBinding.tvItemUserName.text = user.name.first
            println("iysdgfybbyujeruuhrfrtrthrthrt6h")
        }


//        companion object{
//            @JvmStatic
//            @BindingAdapter("userImage")
//            fun loadImage(view: ImageView, url: String){
//                Glide.with(view.context)
//                    .load(url)
//                    .into(view)
//            }
//        }
    }

    inner class HeaderViewHolder(
        private val binding: ItemUserCategoryTitleBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bindData(category: String){
            this.binding.tvCategory.text = category.toUpperCase(Locale.getDefault())
            println("iysdgfybbyujeruuhrfrtrthrthrt6h")

        }
    }


    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_DATA = 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val user = userList[position]


        if (user is User.Data && holder is UserViewHolder) {
            holder.bindData(user.user)

        } else if (user is User.Category && holder is HeaderViewHolder) {
            holder.bindData(user.category)
        }
    }
}