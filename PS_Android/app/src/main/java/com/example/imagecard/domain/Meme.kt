package com.example.imagecard.domain

import com.example.imagecard.data.remote.dto.MemeDto

data class Meme(
    val box_count: Int,
    val height: Int,
    val id: String,
    val name: String,
    val url: String,
    val width: Int
)

fun MemeDto.toMeme(): Meme {
    return Meme(
        box_count = box_count,
        height = height,
        id = id,
        name = name,
        url=url,
        width = width
    )
}
