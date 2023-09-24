package vef.ter.hw6_1.ui.first

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vef.ter.hw6_1.R
import vef.ter.hw6_1.databinding.ItemBinding
import vef.ter.hw6_1.model.Model


class FirstAdapter(
    val onClickItem: (task: Model) -> Unit,
    val onNoClickItem: (task: Model) -> Unit,
    private val updateTask: (task: Model) -> Unit,
    val onLongClickItem: (task: Model) -> Unit,
) : RecyclerView.Adapter<FirstAdapter.ViewHolder>() {
    private var list = listOf<Model>()
    private val _tasks = mutableListOf<Model>()
    fun addData(list: List<Model>) {
        this.list = list
        notifyItemRangeInserted(_tasks.size, list.size - _tasks.size)
    }

    inner class ViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(model: Model) = with(binding) {
            cbAdapter.text = model.title
            cbAdapter.isChecked = model.check
            cbAdapter.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) cbAdapter.setTextColor(R.color.green)
            }
            initListeners(model)

        }

        private fun initListeners(taskModel: Model) {
            if (binding.cbAdapter.isChecked) {
                itemView.setOnClickListener { onNoClickItem(taskModel) }
            } else {
                itemView.setOnClickListener { onClickItem(taskModel) }
            }

            itemView.setOnLongClickListener {
                onLongClickItem(taskModel)
                true
            }
            binding.img.setOnClickListener {
                updateTask(taskModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}