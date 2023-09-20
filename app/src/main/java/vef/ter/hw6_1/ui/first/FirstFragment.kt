package vef.ter.hw6_1.ui.first

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vef.ter.hw6_1.R
import vef.ter.hw6_1.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var firstModel: FirstModel

    private var adapter = FirstAdapter(this::onClickItem, this::showAlertDialog)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstModel = ViewModelProvider(this)[FirstModel::class.java]
        initRV()
        init()

    }

    private fun initRV() = with(binding) {

        firstModel.data.observe(viewLifecycleOwner) { list ->
            adapter.addData(list)
        }
        rv.adapter = adapter

    }

    private fun init()= with(binding) {
        fab.setOnClickListener {
findNavController().navigate(R.id.detailFragment)
        }
    }

    private fun onClickItem(position: Int) {
        firstModel.setActive(position)
    }
    private fun showAlertDialog(position: Int) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Удалить")
            .setMessage("вы точно хотите удалить").setCancelable(true)
            .setPositiveButton("Да") { _, _ ->
                firstModel.deleteTask(position)
            }.setNegativeButton("Нет") { _, _ -> }.show()
    }

}

