package vef.ter.hw6_1.model

class ForModel {
    companion object {
        private var arrayList = arrayListOf<Model>()
        val list: ArrayList<Model>
            get() = arrayList

        fun addData(title: String) {
            arrayList.add(Model(title))
        }

        fun setActive(position: Int){
            arrayList[position].check = true
        }

        fun deleteTask(position: Int) {
            arrayList.removeAt(position)
        }
    }
}
