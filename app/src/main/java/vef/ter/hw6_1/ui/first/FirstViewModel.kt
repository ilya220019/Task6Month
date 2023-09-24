package vef.ter.hw6_1.ui.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vef.ter.hw6_1.model.Model
import vef.ter.hw6_1.utils.ToScreen

@Suppress("UNCHECKED_CAST")
class FirstViewModel : ViewModel() {
    private var data = mutableListOf<Model>()
    private var _sort = ToScreen.ALL
    private val _liveData = MutableLiveData<MutableList<Model>>(mutableListOf())
    val liveData: LiveData<List<Model>>
        get() = _liveData as LiveData<List<Model>>

    fun sortList(sort: ToScreen) {
        _sort = sort
        setActiveToDisplay()
    }

    private fun setActiveToDisplay() {
        when (_sort) {
            ToScreen.ACTIVE -> showActive()
            ToScreen.NOT_ACTIVE -> noActive()
            else -> showAll()
        }
    }

    private fun showActive() {
        val sortedList = mutableListOf<Model>()
        data.forEach { task ->
            if (task.check)
                sortedList.add(task)
        }
        _liveData.value = sortedList
    }

    private fun showAll() {
        _liveData.value = data
    }

    fun addTask(title: String) {
        data.add(Model(id = data.size, title = title))
        _liveData.value = data
    }

    fun setActive(task: Model) {
        data[task.id].check = true
        _liveData.value = data
    }

    fun setNoActive(task: Model) {
        data[task.id].check = false
        _liveData.value = data
    }

    fun deleteTask(task: Model) {
        data.removeAt(task.id)
        _liveData.value = data
    }

    fun updateTask(task: Model) {
        deleteTask(task)
        data.add(task.id, task)
        setActiveToDisplay()
    }

    private fun noActive() {
        val sortedList = mutableListOf<Model>()
        data.forEach { task ->
            if (!task.check)
                sortedList.add(task)
        }
        _liveData.value = sortedList
    }
}