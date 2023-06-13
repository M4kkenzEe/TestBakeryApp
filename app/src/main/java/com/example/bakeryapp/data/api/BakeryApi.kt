package com.example.bakeryapp.data.api

import com.example.bakeryapp.data.model.ResponseCategories
import com.example.bakeryapp.data.model.ResponseDishes
import retrofit2.Response
import retrofit2.http.GET

interface BakeryApi {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategories(): ResponseCategories

    @GET("aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    suspend fun getDishes(): ResponseDishes
}


