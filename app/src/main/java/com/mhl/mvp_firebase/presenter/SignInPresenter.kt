package com.mhl.mvp_firebase.presenter

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mhl.mvp_firebase.utils.Auth
import com.mhl.mvp_firebase.view.SignInView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class SignInPresenter : MvpPresenter<SignInView>() {

    private val authValidator = Auth()

    fun signIn(email: String, password: String) {
        if (!authValidator.validateEmail(email)) {
            viewState.showError(message = "Field(-s) has wrong format")
            return
        }
        if (!authValidator.validatePassword(password)) {
            viewState.showError(message = "Field(-s) has wrong format")
            return
        }

        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!it.isSuccessful) {
                viewState.showError(message = "User with that data didn't exists or check your internet connection")
            } else {
                viewState.auth()
            }
        }
    }

    override fun onFirstViewAttach() {
        if (Firebase.auth.currentUser != null) {
            viewState.auth()
        }
    }
}