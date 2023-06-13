package com.example.bakeryapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private val converterGson: GsonConverterFactory = GsonConverterFactory.create()
    private val BASE_URL = "https://run.mocky.io/v3/"
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(logger)

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converterGson)
        .client(client.build())
        .build()


    fun createApi(): BakeryApi = retrofit.create(BakeryApi::class.java)

}