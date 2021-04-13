package com.sathish.pmapp.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sathish.pmapp.R
import com.sathish.pmapp.database.entity.Project
import com.sathish.pmapp.databinding.ProjectFragmentBinding
import com.sathish.pmapp.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProjectFragment : BaseFragment() {

    private val viewModel: ProjectViewModel by viewModel()
    private lateinit var binding: ProjectFragmentBinding
    private val adapter = ProjectAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProjectFragmentBinding.inflate(inflater, container, false)
        binding.adapter = adapter
        setObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()

    }

    private fun setObserver() {
        viewModel.allProjects.observe(viewLifecycleOwner,  {
            adapter.update(it as ArrayList<Project>)
        })
    }

    private fun onClick() {
        binding.clickListener = View.OnClickListener {
            it?.findNavController()?.navigate(R.id.action_addProject)

        }
    }

}