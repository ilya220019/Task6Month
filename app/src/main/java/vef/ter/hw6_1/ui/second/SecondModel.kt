package vef.ter.hw6_1.ui.second

import androidx.lifecycle.ViewModel
import vef.ter.hw6_1.model.ForModel

class SecondModel:ViewModel() {
    fun saveTask(title:String){
        ForModel.addData(title)
    }
}