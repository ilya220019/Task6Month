package vef.ter.hw6_1.ui.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import vef.ter.hw6_1.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var secondModel: SecondModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        secondModel = ViewModelProvider(this)[SecondModel::class.java]
        init()

    }

    private fun init()= with(binding) {
        binding.btnSave.setOnClickListener {
            secondModel.saveTask(etTitle.text.toString())
            findNavController().navigateUp()
        }
    }
}