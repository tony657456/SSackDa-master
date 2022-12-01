package com.example.myapplication.dto
import com.example.myapplication.api.API
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilderDto {
    var api : API
    var gson = GsonBuilder().setLenient().create()
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.13:8000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        api = retrofit.create(API::class.java)
    }
}