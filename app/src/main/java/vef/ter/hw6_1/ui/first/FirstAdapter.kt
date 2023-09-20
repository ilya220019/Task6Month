package vef.ter.hw6_1.ui.first

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vef.ter.hw6_1.R
import vef.ter.hw6_1.databinding.ItemBinding
import vef.ter.hw6_1.model.Model


class FirstAdapter(
    val onClickItem: (position: Int) -> Unit,
    val onNoClickItem: (position: Int) -> Unit,
    val onLongClickItem: (position: Int) -> Unit
) : RecyclerView.Adapter<FirstAdapter.ViewHolder>() {
    private var list = listOf<Model>()
    fun addData(list: List<Model>) {
        this.list = list
        FirstViewModel().liveData
    }

    inner class ViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(model: Model) = with(binding) {
            cbAdapter.text = model.title
            cbAdapter.isChecked = model.check
            cbAdapter.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) cbAdapter.setTextColor(R.color.green) else {
                    cbAdapter.isChecked = false
                    cbAdapter.setTextColor(R.color.black)
                }
            }
            if (cbAdapter.isChecked) {
                itemView.setOnClickListener { onNoClickItem(adapterPosition) }
            } else {
                itemView.setOnClickListener { onClickItem(adapterPosition) }
            }

            itemView.setOnLongClickListener {
                onLongClickItem(adapterPosition)
                true
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