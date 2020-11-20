package com.ydh.androidrandomuser

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ydh.androidrandomuser.databinding.ItemUserBinding
import com.ydh.androidrandomuser.model.UserModel

class UserAdapter(
    private val context: Context,
//    private val items: MutableList<UserModel>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var userList = listOf<UserModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {

        val inflater = LayoutInflater.from(context)
        val binding: ItemUserBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.userBinding.user = userList[position]
    }

    fun setData(item: List<UserModel>){
        this.userList = item
        notifyDataSetChanged()
    }

//    private fun getItem(position: Int): UserModel{
//        return items[position]
//    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(
        val userBinding: ItemUserBinding
    ) : RecyclerView.ViewHolder(userBinding.root) {
        private var binding: ItemUserBinding? = null

        init {
            this.binding = userBinding
        }

        companion object{
            @JvmStatic
            @BindingAdapter("userImage")
            fun loadImage(view: ImageView, url: String){
                Glide.with(view.context)
                    .load(url)
                    .into(view)
            }
        }
    }
}