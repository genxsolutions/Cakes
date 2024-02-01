package com.genxsol.cakesapp.data.model

import com.google.gson.annotations.SerializedName

data class CakesItem(
    @SerializedName("desc")
    val desc: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String
)