package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.base.BaseUseCase
import com.zalesskyi.domain.models.Note
import com.zalesskyi.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase
@Inject
constructor(private val notesRepository: NotesRepository): BaseUseCase<Unit, Flow<List<Note>>>() {

    override fun execute(params: Unit): Flow<List<Note>> =
        notesRepository.getNotes()
}