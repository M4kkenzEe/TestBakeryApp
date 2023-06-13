package com.example.bakeryapp.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("name")
    val name: String
)