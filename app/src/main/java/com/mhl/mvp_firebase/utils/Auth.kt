package com.mhl.mvp_firebase.utils

class Auth {
    fun validateEmail(email: String): Boolean {
        val regex = Regex("^[A-z0-9\\-\\_]{5,30}+\\@+[A-z]{2,10}+\\.+[A-z]{2,5}$")
        return regex.matches(email)
    }

    fun validatePassword(password: String): Boolean {
        val regex = Regex("^[A-z0-9\\@\\$\\!\\%\\*\\#\\?\\&]{6,30}$")
        return regex.matches(password)
    }
}