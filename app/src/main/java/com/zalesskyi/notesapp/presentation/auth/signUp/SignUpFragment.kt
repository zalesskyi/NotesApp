package com.zalesskyi.notesapp.presentation.auth.signUp

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import com.zalesskyi.notesapp.R
import com.zalesskyi.notesapp.presentation.BaseFragment
import com.zalesskyi.notesapp.presentation.auth.AuthNavigator
import kotlinx.android.synthetic.main.fragment_signup.*
import javax.inject.Inject

class SignUpFragment : BaseFragment<SignUpViewModel>() {

    override val viewModel: SignUpViewModel by inject<SignUpViewModelImpl>()

    override fun getLayout(): Int = R.layout.fragment_signup

    @Inject
    lateinit var navigator: AuthNavigator

    override fun observeViewModel() {
        viewModel.run {
            signUpLD.observe(viewLifecycleOwner) {
                if (it) {
                    navigator.toMain()
                    activity?.finish()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvGoToSignIn.setOnClickListener {
            navigator.toSignIn()
        }
        btnSignUp.setOnClickListener {
            viewModel.signUp(etLogin.text.toString(), etPassword.text.toString())
        }
    }
}