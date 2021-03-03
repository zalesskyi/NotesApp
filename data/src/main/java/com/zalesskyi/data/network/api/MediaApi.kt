package com.zalesskyi.data.network.api

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface MediaApi {

    suspend fun loadImage(bytes: ByteArray, id: String): String

    suspend fun getImageUrl(id: String): String
}

class MediaApiImpl : MediaApi {

    private val storage = Firebase.storage.reference

    override suspend fun loadImage(bytes: ByteArray, id: String): String =
        suspendCoroutine { continuation ->
            storage.child(id).putBytes(bytes).continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                storage.child(id).downloadUrl
                }.addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result?.toString()?.let(continuation::resume)
                }
            }
        }

    override suspend fun getImageUrl(id: String): String =
        suspendCoroutine { continuation ->
            storage.child(id).downloadUrl.addOnSuccessListener { uri ->
                uri?.path?.let(continuation::resume)
            }
        }
}