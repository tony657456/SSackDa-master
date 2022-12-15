package com.example.myapplication.api

import com.example.myapplication.domain.DmcProduct
import com.example.myapplication.domain.RepresentProduct
import com.example.myapplication.domain.SmcProduct
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// 서버와 통신하기 위한 API
interface API {
    @POST("/representp")
    fun getProductResponse(@Body product: RepresentProduct) : Call<List<RepresentProduct>>
    @POST("/dmcp")
    fun getProductResponse(@Body dmcProduct: DmcProduct) : Call<List<DmcProduct>>
    @POST("/smcp")
    fun getProductResponse(@Body smcProduct: SmcProduct) : Call<List<SmcProduct>>
}