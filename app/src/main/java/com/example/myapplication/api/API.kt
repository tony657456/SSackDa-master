package com.example.myapplication.api

import com.example.myapplication.domain.*
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
    @POST("/alp")
    fun getProductResponse(@Body alproduct: Alproduct) : Call<List<Alproduct>>
    @POST("/bathp")
    fun getProductResponse(@Body bathProduct: BathProduct) : Call<List<BathProduct>>
    @POST("/eboardp")
    fun getProductResponse(@Body eboardProduct: EboardProduct) : Call<List<EboardProduct>>
    @POST("/glassp")
    fun getProductResponse(@Body glassProduct: GlassProduct) : Call<List<GlassProduct>>
    @POST("/isopinkp")
    fun getProductResponse(@Body isopinkProduct: IsopinkProduct) : Call<List<IsopinkProduct>>
    @POST("/mytonp")
    fun getProductResponse(@Body mytonProduct: MytonProduct) : Call<List<MytonProduct>>
    @POST("/spandp")
    fun getProductResponse(@Body spandProduct: SpandProduct) : Call<List<SpandProduct>>
    @POST("/taxp")
    fun getProductResponse(@Body taxProduct: TaxProduct) : Call<List<TaxProduct>>
    @POST("/clibbarsystemp")
    fun getProductResponse(@Body clibbarSystemProduct: ClibbarSystemProduct) : Call<List<ClibbarSystemProduct>>
    @POST("/darukip")
    fun getProductResponse(@Body darukiProduct: DarukiProduct) : Call<List<DarukiProduct>>
    @POST("/gujop")
    fun getProductResponse(@Body gujoProduct: GujoProduct) : Call<List<GujoProduct>>
    @POST("/gypsump")
    fun getProductResponse(@Body gypsumProduct: GypsumProduct) : Call<List<GypsumProduct>>
    @POST("/hapanp")
    fun getProductResponse(@Body happanProduct: HappanProduct) : Call<List<HappanProduct>>
    @POST("/mabrsystemp")
    fun getProductResponse(@Body mbarSystemProduct: MbarSystemProduct) : Call<List<MbarSystemProduct>>
    @POST("/mdfp")
    fun getProductResponse(@Body mdfProduct: MdfProduct) : Call<List<MdfProduct>>
    @POST("/runnerp")
    fun getProductResponse(@Body runnerProduct: RunnerProduct) : Call<List<RunnerProduct>>
    @POST("/studp")
    fun getProductResponse(@Body studProduct: StudProduct) : Call<List<StudProduct>>
}