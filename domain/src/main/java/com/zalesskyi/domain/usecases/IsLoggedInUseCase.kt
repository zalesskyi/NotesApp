package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.base.UseCaseCoroutine
import com.zalesskyi.domain.repository.AuthRepository
import javax.inject.Inject

class IsLoggedInUseCase
@Inject
constructor(private val authRepository: AuthRepository): UseCaseCoroutine<Boolean, Unit>() {

    override suspend fun executeOnBackground(params: Unit): Boolean =
        params.run { authRepository.isLoggedIn()}
}