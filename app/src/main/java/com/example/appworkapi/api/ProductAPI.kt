package com.example.appworkapi.api

import com.example.appworkapi.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductAPI {
    @GET("getdata.php")
    fun getProductAPI(): Call<List<Product>>

}