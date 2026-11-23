package com.example.grimriodehogwarts

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val api: HogwartsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://hogwarts-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HogwartsApi::class.java)
    }
}