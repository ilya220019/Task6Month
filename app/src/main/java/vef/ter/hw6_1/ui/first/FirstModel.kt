package vef.ter.hw6_1.ui.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import vef.ter.hw6_1.model.ForModel
import vef.ter.hw6_1.model.Model

class FirstModel : ViewModel() {
    private val _liveData = MediatorLiveData(ForModel.list)
    val data: LiveData<ArrayList<Model>>
        get() = _liveData

    fun setActive(position: Int) {
        ForModel.setActive(position)
        _liveData.value = ForModel.list
    }
    fun deleteTask(position: Int) {
        ForModel.deleteTask(position)
        _liveData.value = ForModel.list
    }
}