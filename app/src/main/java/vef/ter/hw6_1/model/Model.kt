package vef.ter.hw6_1.model

import java.io.Serializable

data class Model(
    var id: Int,
    var title: String? = null,
    var check: Boolean = false
) : Serializable
