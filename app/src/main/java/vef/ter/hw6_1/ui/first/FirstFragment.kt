package vef.ter.hw6_1.ui.first

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vef.ter.hw6_1.R
import vef.ter.hw6_1.base.BaseFragment
import vef.ter.hw6_1.databinding.FragmentFirstBinding
import vef.ter.hw6_1.utils.Const.KEY_ADD_MAIN
import vef.ter.hw6_1.utils.Const.KEY_ADD_NEW_TASK
import vef.ter.hw6_1.utils.Const.KEY_MAIN_ADD
import vef.ter.hw6_1.utils.Const.KEY_TASK_SET
import vef.ter.hw6_1.utils.Const.KEY_UPDATE_TASK
import vef.ter.hw6_1.utils.ToScreen


class FirstFragment : BaseFragment<FragmentFirstBinding, FirstViewModel>() {
    private val adapter = FirstAdapter(
        this::onClickItem, this::onNoClickItem, this::updateTask, this::showAlertDialog
    )
    override val viewModel: FirstViewModel
        get() = ViewModelProvider(this)[FirstViewModel::class.java]

    override fun inflaterViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentFirstBinding.inflate(inflater, container, false)

    override fun initMenu() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.show_all -> {
                        viewModel.sortList(ToScreen.ALL)
                        return true
                    }

                    R.id.show_active -> {
                        viewModel.sortList(ToScreen.ACTIVE)
                        return true
                    }

                    R.id.show_not_active -> {
                        viewModel.sortList(ToScreen.NOT_ACTIVE)
                        return true
                    }

                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun initStart() {
        setFragmentResultListener(KEY_ADD_MAIN) { _, bundle ->
            bundle.getString(KEY_ADD_NEW_TASK)?.let { viewModel.addTask(it) }
            bundle.getSerializable(KEY_UPDATE_TASK)
                ?.let { viewModel.updateTask(it as vef.ter.hw6_1.model.Model) }
        }
    }

    override fun initListener() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.detailFragment)
        }
    }

    override fun initRecyclerView() {
        viewModel.liveData.observe(viewLifecycleOwner) { list ->
            adapter.addData(list)
            binding.rv.adapter = adapter
        }
    }

    private fun onClickItem(task: vef.ter.hw6_1.model.Model) {
        viewModel.setActive(task)
    }

    private fun onNoClickItem(task: vef.ter.hw6_1.model.Model) {
        viewModel.setNoActive(task)
    }

    private fun updateTask(task: vef.ter.hw6_1.model.Model) {
        setFragmentResult(
            KEY_MAIN_ADD,
            bundleOf(KEY_TASK_SET to task)
        )
        findNavController().navigate(R.id.detailFragment)
    }

    private fun showAlertDialog(task: vef.ter.hw6_1.model.Model) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle(getString(R.string.delete)).setMessage(getString(R.string.delete_un))
            .setCancelable(true).setPositiveButton(getString(R.string.yes)) { _, _ ->
                viewModel.deleteTask(task)
            }.setNegativeButton(getString(R.string.no)) { _, _ -> }.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override val firstViewModel: FirstViewModel
        get() = TODO("Not yet implemented")

}

