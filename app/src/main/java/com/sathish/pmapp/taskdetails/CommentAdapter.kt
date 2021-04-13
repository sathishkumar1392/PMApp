package com.sathish.pmapp.taskdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sathish.pmapp.database.entity.Comment
import com.sathish.pmapp.databinding.CommentItemsBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private var items: MutableList<Comment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CommentItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(data: ArrayList<Comment>) {
        items = data
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: CommentItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Comment) {
            binding.itemDetails = item
        }

    }

}