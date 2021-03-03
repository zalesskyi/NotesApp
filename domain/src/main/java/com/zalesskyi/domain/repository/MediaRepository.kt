package com.zalesskyi.domain.repository

interface MediaRepository {

    suspend fun loadImage(bytes: ByteArray, id: String): String

    suspend fun getImageUrl(id: String): String
}