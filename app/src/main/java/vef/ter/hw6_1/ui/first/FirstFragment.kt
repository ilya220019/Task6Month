package vef.ter.hw6_1.ui.first

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vef.ter.hw6_1.R
import vef.ter.hw6_1.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var firstViewModel: FirstViewModel

    private var adapter =
        FirstAdapter(this::onClickItem, this::onNoClickItem, this::showAlertDialog)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBegin()
        initRV()
        init()
    }

    private fun initBegin() {
        firstViewModel = ViewModelProvider(this)[FirstViewModel::class.java]
        setFragmentResultListener("title") { _, bundle ->
            bundle.getString("key1")?.let { firstViewModel.addTask(it) }
        }
    }

    private fun initRV() = with(binding) {

        firstViewModel.liveData.observe(viewLifecycleOwner) { list ->
            adapter.addData(list)
            rv.adapter = adapter
        }
    }

    private fun init() = with(binding) {
        fab.setOnClickListener {
            findNavController().navigate(R.id.detailFragment)
        }
    }

    private fun onClickItem(position: Int) {
        firstViewModel.setActive(position)

    }

    private fun onNoClickItem(position: Int) {
        firstViewModel.setNoActive(position)

    }

    private fun showAlertDialog(position: Int) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Удалить")
            .setMessage("вы точно хотите удалить").setCancelable(true)
            .setPositiveButton("Да") { _, _ ->
                firstViewModel.deleteTask(position)
            }.setNegativeButton("Нет") { _, _ -> }.show()
    }
}

