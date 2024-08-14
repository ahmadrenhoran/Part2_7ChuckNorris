package com.ahmadrenhoran.part2_7chucknorris.data.repository

import com.ahmadrenhoran.part2_7chucknorris.data.model.JokesResponse
import com.ahmadrenhoran.part2_7chucknorris.data.model.Response
import com.ahmadrenhoran.part2_7chucknorris.data.remote.ChuckNorrisApiService

class JokeRepository(private val apiService: ChuckNorrisApiService) {

    suspend fun searchJokes(query: String): Response<JokesResponse> {
        return try {
            val response = apiService.searchJokes(query)
            if (response.isSuccessful) {
                response.body()?.let { jokes ->
                    Response.Success(jokes)
                } ?: Response.Failure(Exception("No jokes found"))
            } else {
                Response.Failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Response.Failure(Exception(e.message ?: "An error occurred"))
        }
    }
}
