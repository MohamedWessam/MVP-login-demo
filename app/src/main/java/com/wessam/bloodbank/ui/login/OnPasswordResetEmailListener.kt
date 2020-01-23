package com.wessam.bloodbank.ui.login

interface OnPasswordResetEmailListener {

    fun onEmailSent()

    fun onResetPasswordUnknownError(error: String)

}