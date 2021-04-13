package com.sathish.pmapp.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sathish.pmapp.database.entity.Task
import com.sathish.pmapp.databinding.TaskFragmentBinding
import com.sathish.pmapp.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskFragment : BaseFragment() {

    private val viewModel: TaskViewModel by viewModel()
    private lateinit var binding: TaskFragmentBinding
    private val adapter = TaskAdapter()
    private val args by lazy {
        TaskFragmentArgs.fromBundle(requireArguments())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TaskFragmentBinding.inflate(inflater, container, false)
        binding.adapter = adapter
        setObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        backToProject()
    }

    private fun backToProject() {
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }



    private fun onClick() {
        val projectId = args.Id
        binding.clickListener = View.OnClickListener {
            val destination = TaskFragmentDirections.actionAddTask(projectId)
            it?.findNavController()?.navigate(destination)
        }
    }

    private fun setObserver() {

        val projectId = args.Id
        viewModel.getTaskList(projectId)

        viewModel.allTask.observe(viewLifecycleOwner, Observer {
            adapter.update(it as ArrayList<Task>)
        })

    }

}
