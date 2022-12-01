package com.example.myapplication.api

import com.example.myapplication.domain.Product
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("android")
    fun getProductResponse(@Body product: Product) : Call<List<Product>>
}