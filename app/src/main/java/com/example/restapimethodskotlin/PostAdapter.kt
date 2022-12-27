package com.example.restapimethodskotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.restapimethodskotlin.databinding.PostItemBinding

class PostAdapter(private val data: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder> (){
    var binding: PostItemBinding? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.post_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.txtBody?.text=data[position].body
        holder.txtId?.text=data[position].id
        holder.txtTitle?.text=data[position].title
        holder.txtUserId?.text=data[position].userId
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemBind: PostItemBinding?) : RecyclerView.ViewHolder(itemBind?.root!!) {
        var txtTitle : TextView?= binding?.titleTv
        var txtUserId: TextView?=binding?.userIdTv
        var txtId: TextView?=binding?.idTv
        var txtBody: TextView?=binding?.bodyTv



    }
}
