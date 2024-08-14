package com.ahmadrenhoran.part2_7chucknorris.data.model

data class Joke(
    val id: String,
    val value: String,
    val jokeImage: String
)

data class JokesResponse(
    val total: Int,
    val result: List<Joke>
)