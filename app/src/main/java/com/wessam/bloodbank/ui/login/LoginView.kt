package com.wessam.bloodbank.ui.login

interface LoginView {

    fun openSignUpActivity()

    fun openMainActivity()

    fun setEmailEmptyError()

    fun setEmailWrongError()

    fun setPasswordEmptyError()

    fun showEmailSentToast()

    fun showUnknownErrorToast(error: String)

    fun showProgress()

    fun hideProgress()

}