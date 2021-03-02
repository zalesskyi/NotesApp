package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.base.UseCaseCoroutine
import com.zalesskyi.domain.models.Note
import com.zalesskyi.domain.repository.NotesRepository
import javax.inject.Inject

class AddNoteUseCase
@Inject
constructor(private val notesRepository: NotesRepository): UseCaseCoroutine<Boolean, AddNoteUseCase.Params>() {

    data class Params(val note: Note)

    override suspend fun executeOnBackground(params: Params): Boolean =
        params.run { notesRepository.addNote(note) }
}