package com.zalesskyi.notesapp.presentation.main.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.zalesskyi.notesapp.R
import com.zalesskyi.notesapp.presentation.BaseFragment
import com.zalesskyi.notesapp.presentation.main.MainNavigator
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : BaseFragment<ListViewModel>() {

    override val viewModel: ListViewModel by inject<ListViewModelImpl>()

    override fun getLayout(): Int = R.layout.fragment_list

    @Inject
    lateinit var navigator: MainNavigator

    private val adapter = NotesListAdapter()

    override fun observeViewModel() {
        viewModel.run {
            notesLD.observe(viewLifecycleOwner, adapter::updateAllNotify)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvList.run {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ListFragment.adapter
        }
        fabAddNote.setOnClickListener {
            navigator.toCreateNewNote()
        }
    }
}