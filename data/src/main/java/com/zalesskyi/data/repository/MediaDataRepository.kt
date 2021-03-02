package com.zalesskyi.data.repository

import com.zalesskyi.data.network.api.MediaApi
import com.zalesskyi.domain.repository.MediaRepository
import javax.inject.Inject

class MediaDataRepository
@Inject
constructor(private val mediaApi: MediaApi) : MediaRepository {

    override suspend fun loadImage(bytes: ByteArray, id: String): String =
        mediaApi.loadImage(bytes, id)

    override suspend fun getImageUrl(id: String): String =
        mediaApi.getImageUrl(id)
}