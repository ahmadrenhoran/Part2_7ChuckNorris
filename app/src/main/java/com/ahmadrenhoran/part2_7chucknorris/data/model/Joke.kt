package com.ahmadrenhoran.part2_7chucknorris.data.model

import com.google.gson.annotations.SerializedName

data class Joke(
    val id: String,
    val value: String,
    @field:SerializedName("icon_url")
    val iconUrl: String
)

data class JokesResponse(
    val total: Int,
    val result: List<Joke>
)