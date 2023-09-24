package vef.ter.hw6_1.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import vef.ter.hw6_1.ui.first.FirstViewModel

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {
    protected var _binding: VB? = null
    protected abstract val viewModel: VM
    protected val binding get() = _binding!!
    protected abstract fun inflaterViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflaterViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStart()
        initRecyclerView()
        initMenu()
        initListener()
    }

    open fun initStart() {}

    open fun initRecyclerView() {}

    open fun initMenu() {}

    open fun initListener() {}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract val firstViewModel: FirstViewModel
}