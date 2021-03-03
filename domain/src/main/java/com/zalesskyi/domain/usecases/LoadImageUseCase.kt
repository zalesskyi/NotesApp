package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.base.UseCaseCoroutine
import com.zalesskyi.domain.repository.MediaRepository
import javax.inject.Inject

class LoadImageUseCase
@Inject
constructor(private val mediaRepository: MediaRepository): UseCaseCoroutine<String, LoadImageUseCase.Params>() {

    class Params(val bytes: ByteArray, val id: String)

    override suspend fun executeOnBackground(params: Params): String =
        params.run { mediaRepository.loadImage(bytes, id) }
}