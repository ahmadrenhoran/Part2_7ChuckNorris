package com.ahmadrenhoran.part2_7chucknorris.data.remote

import com.ahmadrenhoran.part2_7chucknorris.data.model.JokesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ChuckNorrisApiService {
    @GET("search")
    suspend fun searchJokes(@Query("query") query: String): Response<JokesResponse>
}