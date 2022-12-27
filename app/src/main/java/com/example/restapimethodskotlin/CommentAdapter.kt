package com.example.restapimethodskotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.restapimethodskotlin.databinding.RecLayoutBinding

class CommentAdapter(private val data: List<Comment?>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>(){
private var binding: RecLayoutBinding?=null;



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       binding= DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.rec_layout,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.body?.text= data[position]?.commentText
        holder.email?.text= data[position]?.email
        holder.postId?.text= data[position]?.postId
        holder.txtId?.text= data[position]?.id
        holder.txtName?.text= data[position]?.name

    }

    override fun getItemCount(): Int {
        return data.size
    }
    inner class ViewHolder(itemBind : RecLayoutBinding?) : RecyclerView.ViewHolder(itemBind?.root!!) {
        var txtName: TextView? = binding?.userName
        var txtId : TextView?=binding?.commentId
        var postId: TextView?=binding?.postId
        var email : TextView?=binding?.commentEmail
        var body: TextView?=binding?.commentText
    }
}

