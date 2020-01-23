package com.wessam.bloodbank.ui.signup

interface SignUpPresenter {

    fun validateCredentials(email: String, password: String,confirmPassword: String)

    fun onDestroy()

}