package com.sathish.pmapp.taskdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.findNavController
import com.sathish.pmapp.R
import com.sathish.pmapp.database.entity.Comment
import com.sathish.pmapp.database.entity.Task
import com.sathish.pmapp.databinding.TaskDetailFragmentBinding
import com.sathish.pmapp.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskDetailFragment : BaseFragment() {


    private val viewModel: TaskDetailViewModel by viewModel()
    private val adapter: CommentAdapter = CommentAdapter()
    private lateinit var binding: TaskDetailFragmentBinding
    private var prjReferId = 0
    private var taskId = 0
    private val args by lazy {
        TaskDetailFragmentArgs.fromBundle(requireArguments())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TaskDetailFragmentBinding.inflate(inflater, container, false)
        binding.adapter = adapter
        binding.viewmodel = viewModel
        mappingData(args)
        setObserver()
        return binding.root
    }

    private fun setObserver() {
        viewModel.allComments.observe(viewLifecycleOwner, {
            adapter.update(it as ArrayList<Comment>)
        })

        viewModel.successMessage.observe(viewLifecycleOwner, {
            when (it) {
                getString(R.string.str_update_status_task) -> {
                    showMessage(it)
                    hideKeyBoard()
                    screenNavigate()
                    clearValue()
                }

                getString(R.string.str_comment_status) -> {
                    showMessage(it)
                    hideKeyBoard()
                    binding.edtViewTaskComment.text.clear()
                }

                else -> {
                    showMessage(it)
                    screenNavigate()
                }

            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            showMessage(it)
        })


    }

    private fun screenNavigate() {
        val destination = TaskDetailFragmentDirections.actionToTaskList()
        requireActivity().findNavController(id).navigate(destination)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onClick()
        iconVisibility()
        onBackPressed()

    }

    private fun onBackPressed() {
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }


    private fun iconVisibility() {
        binding.edtViewTaskComment.doOnTextChanged { text, _, _, _ ->
            if (text!!.isNotEmpty()) {
                binding.imgBtnCommentSave.visibility = View.VISIBLE

            } else {
                binding.imgBtnCommentSave.visibility = View.GONE
            }
        }
    }

    private fun onClick() {
        var editTag = 0
        binding.onClickListener = View.OnClickListener {
            when (editTag) {
                0 -> {
                    setEditable()
                    changeToolBarIconToSave()
                    editTag = 1
                }
                1 -> {
                    updateTask()
                    clearValue()
                    editTag = 0

                }
            }

        }


    }


    private fun updateTask() {
        val name = binding.edtViewTaskName.text.toString()
        val desc = binding.edtViewTaskDesc.text.toString()
        val modifiedDate = viewModel.getModifiedDate()
        val status = binding.txtViewTaskStatus.text.toString()
        val updateTask = Task(
            taskId = taskId,
            prjReferenceId = prjReferId,
            taskName = name,
            description = desc,
            createdDate = modifiedDate,
            Status = status
        )
        viewModel.updateTask(updateTask)

    }

    private fun changeToolBarIconToSave() {
        binding.btnUpdate.setBackgroundResource(R.drawable.ic_save)
    }

    private fun setEditable() {
        binding.edtViewTaskName.isEnabled = true
        binding.edtViewTaskDesc.isEnabled = true
    }

    private fun mappingData(args: TaskDetailFragmentArgs) {
        binding.edtViewTaskName.setText(args.taskName)
        binding.edtViewTaskDesc.setText(args.taskDesc)
        binding.txtViewTaskDate.text = args.taskDate
        binding.txtViewTaskStatus.text = args.taskStatus
        taskId = args.taskId
        prjReferId = args.prjReferenceId
        viewModel.taskId = args.taskId
        viewModel.taskStatus = args.taskStatus
        viewModel.getComments()
    }


    private fun clearValue() {
        binding.edtViewTaskName.text.clear()
        binding.edtViewTaskDesc.text.clear()
        binding.edtViewTaskComment.text.clear()
    }
}