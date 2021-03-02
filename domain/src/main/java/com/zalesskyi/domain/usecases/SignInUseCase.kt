package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.base.UseCaseCoroutine
import com.zalesskyi.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase
@Inject
constructor(private val authRepository: AuthRepository): UseCaseCoroutine<Boolean, SignInUseCase.Params>() {

    data class Params(val email: String, val password: String)

    override suspend fun executeOnBackground(params: Params): Boolean =
          params.run { authRepository.signIn(email, password) }
}