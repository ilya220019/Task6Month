package vef.ter.hw6_1.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import vef.ter.hw6_1.MainActivity
import vef.ter.hw6_1.R
import vef.ter.hw6_1.base.BaseFragment
import vef.ter.hw6_1.databinding.FragmentDetailBinding
import vef.ter.hw6_1.model.Model
import vef.ter.hw6_1.ui.first.FirstViewModel
import vef.ter.hw6_1.utils.Const.KEY_ADD_MAIN
import vef.ter.hw6_1.utils.Const.KEY_ADD_NEW_TASK
import vef.ter.hw6_1.utils.Const.KEY_MAIN_ADD
import vef.ter.hw6_1.utils.Const.KEY_TASK_SET
import vef.ter.hw6_1.utils.Const.KEY_UPDATE_TASK

class DetailFragment : BaseFragment<FragmentDetailBinding, ViewModel>() {
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    override fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailBinding.inflate(inflater, container, false)

    private var _task: Model? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    override fun initStart() {
        super.initStart()
        setFragmentResultListener(KEY_MAIN_ADD) { _, bundle ->
            bundle.getSerializable(KEY_TASK_SET)?.let { task ->
                _task = task as Model
                binding.cbState.isChecked = task.check
                _task!!.check = binding.cbState.isChecked
                initView()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView() {
        _task?.let { task ->
            binding.btnSave.text = getString(R.string.update)
            binding.etTitle.setText(task.title)
            binding.cbState.visibility = View.VISIBLE

            binding.cbState.isChecked = task.check
        }
    }

    override fun initListener() {
        binding.btnSave.setOnClickListener {
            if (_task == null)
                addNewTask()
            else {
                _task!!.title = binding.etTitle.text.toString()
                _task!!.check = binding.cbState.isChecked
                updateTask()
            }
        }
    }

    override val firstViewModel: FirstViewModel
        get() = TODO("Not yet implemented")

    private fun updateTask() {
        setFragmentResult(
            KEY_MAIN_ADD,
            bundleOf(KEY_UPDATE_TASK to _task)

        )
        findNavController().navigateUp()
    }

    private fun addNewTask() {
        setFragmentResult(
            KEY_ADD_MAIN,
            bundleOf(KEY_ADD_NEW_TASK to binding.etTitle.text.toString())
        )
        findNavController().navigateUp()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}