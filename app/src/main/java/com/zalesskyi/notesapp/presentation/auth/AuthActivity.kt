package com.zalesskyi.notesapp.presentation.auth

import android.content.Context
import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.zalesskyi.notesapp.R
import com.zalesskyi.notesapp.navigation.AppNavProvider
import com.zalesskyi.notesapp.presentation.BaseActivity

class AuthActivity : BaseActivity<AuthViewModel>(), AppNavProvider {

    companion object {

        fun getIntent(context: Context) =
            Intent(context, AuthActivity::class.java)
    }

    override val layoutResId = R.layout.activity_auth

    override val viewModel: AuthViewModel by inject<AuthViewModelImpl>()

    override fun getNavController(): NavController = findNavController(R.id.nav_host_fragment)

    override fun observeViewModel() = Unit
}