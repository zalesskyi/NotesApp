package com.zalesskyi.domain.repository

import com.zalesskyi.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun addNote(note: Note): Boolean

    fun getNotes(): Flow<List<Note>>
}