package com.zalesskyi.data.network.api

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.zalesskyi.domain.models.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface NotesApi {

    suspend fun addNote(note: Note): Boolean

    fun getNotes(): Flow<List<Note>>
}

class NotesApiImpl
@Inject
constructor(): NotesApi {

    companion object {

        private const val NOTES_ENDPOINT = "notes"
    }

    private val databaseReference = Firebase.database.reference.child(NOTES_ENDPOINT)

    override suspend fun addNote(note: Note): Boolean =
        suspendCoroutine { continuation ->
            databaseReference.child(note.id).setValue(note).addOnCompleteListener {
                continuation.resume(it.isSuccessful)
            }.addOnFailureListener {
                it
            }
        }


    @ExperimentalCoroutinesApi
    override fun getNotes(): Flow<List<Note>> =
        callbackFlow {
            databaseReference.addValueEventListener(object : ValueEventListener {

                override fun onCancelled(error: DatabaseError) = Unit

                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.getValue<HashMap<String, Note>>()?.values?.toList()?.let(::offer)
                }
            })
            awaitClose { cancel() }
    }
}