package com.zalesskyi.data.repository

import com.zalesskyi.data.network.api.AuthApi
import com.zalesskyi.data.store.LocalStore
import com.zalesskyi.domain.repository.AuthRepository
import javax.inject.Inject

class AuthDataRepository
@Inject
constructor(private val authApi: AuthApi,
            private val localStore: LocalStore) : AuthRepository {

    override suspend fun signIn(email: String, password: String) =
          authApi.signIn(email, password).also {
              localStore.setEmail(email)
          }

    override suspend fun signUp(email: String, password: String) =
          authApi.signUp(email, password).also {
              localStore.setEmail(email)
          }

    override fun isLoggedIn(): Boolean = localStore.getEmail() != null
}