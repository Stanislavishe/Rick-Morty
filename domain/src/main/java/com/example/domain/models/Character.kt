package com.example.domain.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    val id: Int,
    val name: String,
    val status: String?,
    val species: String?,
    val gender: String?,
    val origin: Origin,
    val location: Origin,
    val image: String?,
    val episode: List<String>?
)
