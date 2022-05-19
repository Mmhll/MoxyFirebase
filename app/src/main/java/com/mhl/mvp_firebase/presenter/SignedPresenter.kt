package com.mhl.mvp_firebase.presenter

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mhl.mvp_firebase.dataclasses.Professions
import com.mhl.mvp_firebase.view.SignedView
import moxy.MvpPresenter


class SignedPresenter : MvpPresenter<SignedView>() {

    fun getData() {
        val data = ArrayList<Any>()
        Firebase.database.getReference("Professions").get().addOnSuccessListener {
            if (it.exists()){
                it.children.forEach { snap ->
                    data.add(snap.getValue(Professions::class.java)!!)
                }
                viewState.recyclerFill(data = data)
            }
            else {
                viewState.dataIsEmpty()
            }
        }
    }

    fun signOut() {
        Firebase.auth.signOut()
        viewState.signOut()
    }

}