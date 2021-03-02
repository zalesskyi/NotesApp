package com.zalesskyi.notesapp.presentation.main.create

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import com.zalesskyi.notesapp.R
import com.zalesskyi.notesapp.ext.loadImage
import com.zalesskyi.notesapp.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_create_note.*


class CreateNoteFragment : BaseFragment<CreateNoteViewModel>() {

    companion object {

        private const val LOAD_IMG_REQUEST_CODE = 123
    }

    override val viewModel: CreateNoteViewModel by inject<CreateNoteViewModelImpl>()

    override fun getLayout(): Int = R.layout.fragment_create_note

    override fun observeViewModel() {
        viewModel.run {
            noteCreatedLD.observe(viewLifecycleOwner) {
                if (it) activity?.onBackPressed()
            }
            imageLD.observe(viewLifecycleOwner, ivImage::loadImage)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bAddNote.setOnClickListener {
            viewModel.createNote(etDescription.text.toString())
        }
        bAttach.setOnClickListener {
            pickFromGallery()
        }
    }

    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        startActivityForResult(intent, LOAD_IMG_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == LOAD_IMG_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                context?.contentResolver?.let { resolver ->
                    viewModel.loadImage(uri, resolver)
                }
            }
        }
    }
}