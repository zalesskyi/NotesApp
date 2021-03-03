package com.zalesskyi.notesapp.presentation.main.create

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zalesskyi.domain.models.Note
import com.zalesskyi.domain.usecases.AddNoteUseCase
import com.zalesskyi.domain.usecases.LoadImageUseCase
import com.zalesskyi.notesapp.presentation.BaseViewModel
import com.zalesskyi.notesapp.presentation.BaseViewModelImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import java.io.ByteArrayOutputStream
import java.util.*
import javax.inject.Inject

interface CreateNoteViewModel : BaseViewModel {

    val noteCreatedLD: LiveData<Boolean>

    val imageLD: LiveData<String>

    fun createNote(description: String)

    fun loadImage(data: Uri, contentResolver: ContentResolver)
}

class CreateNoteViewModelImpl
@Inject
constructor(private val createNoteUseCase: AddNoteUseCase,
            private val loadImageUseCase: LoadImageUseCase) : BaseViewModelImpl(), CreateNoteViewModel {

    override val noteCreatedLD = MutableLiveData<Boolean>()

    override val imageLD = MutableLiveData<String>()

    override fun createNote(description: String) {
        viewModelScope.launch {
            createNoteUseCase.execute(AddNoteUseCase.Params(
                Note(UUID.randomUUID().toString(), description, imageLD.value, DateTime.now().toString()))) {
                onComplete = {
                    noteCreatedLD.postValue(it)
                }
            }
        }
    }

    override fun loadImage(data: Uri, contentResolver: ContentResolver) {
        viewModelScope.launch(Dispatchers.IO) {
            contentResolver.openInputStream(data)?.use { stream ->
                BitmapFactory.decodeStream(stream).let { bitmap ->
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val bytes = baos.toByteArray()
                    data.path?.let {
                        loadImageUseCase.execute(LoadImageUseCase.Params(bytes, it)) {
                            onComplete = {
                                imageLD.postValue(it)
                            }
                        }
                    }
                }
            }
        }
    }
}