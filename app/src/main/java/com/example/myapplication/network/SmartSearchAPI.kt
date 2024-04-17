package com.example.myapplication.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

data class RequestBody(
    val prompt: String,
    val max_tokens: Int
)

data class CompletionResponse(
    val id: String,
    val choices: List<Choice>
)

data class Choice(
    val text: String,
    val index: Int,
    val logprobs: Any?,
    val finish_reason: String
)

interface OpenAIApiService {
    @POST("v1/engines/text-davinci-003/completions")
    @Headers("Authorization: Bearer YOUR_API_KEY")

    fun createCompletion(@Body body: RequestBody): Call<CompletionResponse>
}
