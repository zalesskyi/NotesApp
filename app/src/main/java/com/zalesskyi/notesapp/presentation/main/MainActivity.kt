package com.zalesskyi.notesapp.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.zalesskyi.notesapp.R
import com.zalesskyi.notesapp.navigation.AppNavProvider
import com.zalesskyi.notesapp.presentation.BaseActivity

class MainActivity : BaseActivity<MainViewModel>(), AppNavProvider {

    companion object {

        fun getIntent(context: Context) =
            Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override val layoutResId = R.layout.activity_main

    override val viewModel: MainViewModel by inject<MainViewModelImpl>()

    override fun observeViewModel() = Unit

    override fun getNavController(): NavController = findNavController(R.id.nav_host_fragment)
}