package com.sathish.pmapp.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sathish.pmapp.databinding.AddProjectFragmentBinding
import com.sathish.pmapp.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProjectFragment : BaseFragment() {

    private lateinit var binding: AddProjectFragmentBinding
    private val viewModel: AddProjectViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddProjectFragmentBinding.inflate(inflater, container, false)
        binding.viewModel =  viewModel
        setObserver()
        onBackPressed()
        return binding.root
    }

    private fun onBackPressed() {
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setObserver() {
        viewModel.errorMessage.observe(viewLifecycleOwner, {
             showMessage(it)
        })

        viewModel.successMessage.observe(viewLifecycleOwner,  {
            showMessage(it)
            hideKeyBoard()
            screenNavigate()
            clearValue()
        })


    }

    private fun screenNavigate() {
        val destination = AddProjectFragmentDirections.actionSaveProject()
        requireActivity().findNavController(id).navigate(destination)
    }


    private fun clearValue() {
        binding.editTextProject.text.clear()
        binding.editTextDescription.text.clear()
    }
}


