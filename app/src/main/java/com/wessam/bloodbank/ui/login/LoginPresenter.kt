package com.wessam.bloodbank.ui.login

interface LoginPresenter {

    fun onDestroy()

    fun validateCredentials(email: String, password: String)

    fun sendPasswordResetEmail(email: String)

}