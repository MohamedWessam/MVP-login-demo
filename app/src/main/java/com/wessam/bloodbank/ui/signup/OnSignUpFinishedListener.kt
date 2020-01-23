package com.wessam.bloodbank.ui.signup

interface OnSignUpFinishedListener {

    fun onEmailEmptyError()

    fun onEmailWrongError()

    fun onPasswordEmptyError()

    fun onConfirmPasswordEmptyError()

    fun onPasswordNotMatchError()

    fun onPasswordShortError()

    fun onSuccess()

    fun onUnknownError(error: String)

}