package com.example.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback

class SearchRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val openAIApiService = retrofit.create(OpenAIApiService::class.java)

    fun performSearch(prompt: String, callback: Callback<CompletionResponse>) {
        val requestBody = RequestBody(prompt, 100)
        val call = openAIApiService.createCompletion(requestBody)
        call.enqueue(callback)
    }
}