package com.example.e_trade.service

import com.example.e_trade.model.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {
    // Base Url ->https://raw.githubusercontent.com/
    // atilsamancioglu/BTK23-DataSet/main/products.json

    @GET("atilsamancioglu/BTK23-DataSet/main/products.json")
    suspend fun getData(): Response<List<Products>>
}