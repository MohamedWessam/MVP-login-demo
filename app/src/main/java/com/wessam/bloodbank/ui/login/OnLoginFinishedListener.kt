package com.wessam.bloodbank.ui.login

interface OnLoginFinishedListener {

    fun onEmailEmptyError()

    fun onEmailWrongError()

    fun onPasswordEmptyError()

    fun onSuccess()

    fun onUnknownError(error: String)

}