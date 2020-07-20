package com.sathish.pmapp.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sathish.pmapp.database.entity.Project
import com.sathish.pmapp.databinding.ProjectItemsBinding

class ProjectAdapter : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    private var items: MutableList<Project> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ProjectItemsBinding =
            ProjectItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(data: ArrayList<Project>) {
        items = data
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ProjectItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Project) {
            binding.itemDetails = item
            binding.clickListener = View.OnClickListener {
                val prjId = item.id
                val destination =  ProjectFragmentDirections.actionToTask(prjId)
                it.findNavController().navigate(destination)
            }
        }
    }


}