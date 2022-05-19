package com.mhl.mvp_firebase.dataclasses

data class Professions(
    val about : String? = "",
    val budget : String? = "",
    val code : String? = "",
    val image : String? = "",
    val name : String? = "",
    val years : ArrayList<Years>? = null
)
data class Years(
    val year : String? = "",
    val url : String? = ""
)