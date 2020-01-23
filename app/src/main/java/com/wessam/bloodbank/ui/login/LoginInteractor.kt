package com.wessam.bloodbank.ui.login

interface LoginInteractor {

    fun login(email: String, password: String, listener: OnLoginFinishedListener)

    fun sendPasswordResetEmail(email: String, listener: OnPasswordResetEmailListener)
}