package com.bosta.shoppinglist.services

import com.bosta.shoppinglist.data.Categories
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

// build a connection to the base url
// and convert the response to kotlin object
private val retrofit = Retrofit.Builder()
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val recipeService: ApiService = retrofit.create(ApiService::class.java)
interface ApiService {
    //get keyword is for getting the data from the website we are calling "http request"
    @GET("categories.php") //end point of the url
    //function that get the data and returns categories
    suspend fun getCategories(): Categories
}