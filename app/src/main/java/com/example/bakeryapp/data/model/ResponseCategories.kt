package com.example.bakeryapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseCategories(
    @SerializedName("сategories")
    val categories: List<Category>
)