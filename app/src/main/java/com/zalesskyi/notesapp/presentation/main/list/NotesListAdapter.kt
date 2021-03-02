package com.zalesskyi.notesapp.presentation.main.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zalesskyi.domain.models.Note
import com.zalesskyi.notesapp.R
import com.zalesskyi.notesapp.ext.loadImage
import kotlinx.android.synthetic.main.item_note.view.*

class NotesListAdapter : RecyclerView.Adapter<NotesListAdapter.NoteHolder>() {

    private val items = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder =
        NoteHolder.newInstance(LayoutInflater.from(parent.context), parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateAllNotify(notes: List<Note>) {
        items.run {
            clear()
            addAll(notes)
        }
        notifyDataSetChanged()
    }

    class NoteHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {

            fun newInstance(inflater: LayoutInflater, parent: ViewGroup) =
                NoteHolder(inflater.inflate(R.layout.item_note, parent, false))
        }

        fun bind(note: Note) {
            itemView.apply {
                tvDescription.text = note.description
                tvDate.text = note.creationDate
                note.imageUrl?.let(ivImg::loadImage)
            }
        }
    }
}