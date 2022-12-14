package com.example.myapplication.api

import com.example.myapplication.domain.RepresentProduct
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// 서버와 통신하기 위한 API
interface API {
    @POST("android")
    fun getProductResponse(@Body product: RepresentProduct) : Call<List<RepresentProduct>>
}