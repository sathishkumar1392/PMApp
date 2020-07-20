package com.sathish.pmapp.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sathish.pmapp.database.entity.Task
import com.sathish.pmapp.databinding.TaskItemsBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {


    private var items: MutableList<Task> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TaskItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(data: ArrayList<Task>) {
        items = data
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: TaskItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task) {
            binding.itemDetails = item
            binding.clickListener = View.OnClickListener {
                val taskName = item.taskName
                val desc = item.description
                val createdDate = item.createdDate
                val taskId = item.taskId
                val status = item.Status
                val prjId = item.prjReferenceId

                val destination = TaskFragmentDirections.actionTaskDetail(
                    taskName,
                    desc,
                    createdDate,
                    taskId,
                    prjId,
                    status
                )
                it.findNavController().navigate(destination)
            }

        }

    }

}






