package com.wessam.bloodbank.ui.signup

interface SignUpInteractor {

    fun createUser(email: String, password: String, confirmPassword: String, listener: OnSignUpFinishedListener)

}