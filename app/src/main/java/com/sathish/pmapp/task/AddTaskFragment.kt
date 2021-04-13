package com.sathish.pmapp.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sathish.pmapp.databinding.AddTaskFragmentBinding
import com.sathish.pmapp.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTaskFragment : BaseFragment() {

    private var prjId: Int = 0
    private lateinit var binding: AddTaskFragmentBinding
    private val viewModel: AddTaskViewModel by viewModel()
    private val args by lazy {
        AddTaskFragmentArgs.fromBundle(requireArguments())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddTaskFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
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
        prjId = args.id
        viewModel.prjId = prjId

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            showMessage(it)
        })

        viewModel.successMessage.observe(viewLifecycleOwner, Observer {
            showMessage(it)
            hideKeyBoard()
            screenNavigate()
            clearValue()
        })

    }

    private fun screenNavigate() {
        val destination = AddTaskFragmentDirections.actionTask(prjId)
        requireActivity().findNavController(id).navigate(destination)
    }


    private fun clearValue() {
        binding.editTextTask.text.clear()
        binding.editTextDescription.text.clear()
        binding.editTextComment.text.clear()
    }

}