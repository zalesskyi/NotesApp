package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.base.UseCaseCoroutine
import com.zalesskyi.domain.repository.MediaRepository
import javax.inject.Inject

class GetImageUseCase
@Inject
constructor(private val mediaRepository: MediaRepository): UseCaseCoroutine<String, String>() {

    override suspend fun executeOnBackground(params: String): String =
        mediaRepository.getImageUrl(params)
}