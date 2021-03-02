package com.zalesskyi.domain.repository

interface AuthRepository {

    suspend fun signIn(email: String, password: String): Boolean

    suspend fun signUp(email: String, password: String): Boolean
}