package vef.ter.hw6_1.ui.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vef.ter.hw6_1.model.Model

@Suppress("UNCHECKED_CAST")
class FirstViewModel : ViewModel() {
    private var data = mutableListOf<Model>()
    private val _liveData = MutableLiveData<MutableList<Model>>(mutableListOf())
    val liveData: LiveData<List<Model>>
        get() = _liveData as LiveData<List<Model>>

    fun addTask(title: String) {
        data.add(Model(title = title))
        _liveData.value = data
    }

    fun setActive(position: Int) {
        data[position].check = true
        _liveData.value = data
    }

    fun setNoActive(position: Int) {
        data[position].check = false
        _liveData.value = data
    }

    fun deleteTask(position: Int) {
        data.removeAt(position)
        _liveData.value = data
    }
}