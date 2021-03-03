package com.zalesskyi.data.repository

import com.zalesskyi.data.network.api.NotesApi
import com.zalesskyi.domain.models.Note
import com.zalesskyi.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesDataRepository
@Inject
constructor(private val api: NotesApi) : NotesRepository {

    override suspend fun addNote(note: Note): Boolean = api.addNote(note)

    override fun getNotes(): Flow<List<Note>> = api.getNotes()
}