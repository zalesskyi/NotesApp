package com.zalesskyi.notesapp.presentation.auth.signIn

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import com.zalesskyi.notesapp.R
import com.zalesskyi.notesapp.presentation.BaseFragment
import com.zalesskyi.notesapp.presentation.auth.AuthNavigator
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class SignInFragment : BaseFragment<SignInViewModel>() {

    override val viewModel: SignInViewModel by inject<SignInViewModelImpl>()

    override fun getLayout(): Int = R.layout.fragment_login

    @Inject
    lateinit var navigator: AuthNavigator

    override fun observeViewModel() {
        viewModel.run {
            signInLD.observe(viewLifecycleOwner) {
                if (it) navigator.toMain()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvGoToSignUp.setOnClickListener {
            navigator.toSignUp()
        }
        btnSignIn.setOnClickListener {
            viewModel.signIn(edtLogin.text.toString(), edtPassword.text.toString())
        }
    }
}