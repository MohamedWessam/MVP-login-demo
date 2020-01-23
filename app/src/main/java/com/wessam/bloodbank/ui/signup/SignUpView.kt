package com.wessam.bloodbank.ui.signup

interface SignUpView {

    fun setEmailEmptyError()

    fun setEmailWrongError()

    fun setPasswordEmptyError()

    fun setConfirmPasswordEmptyError()

    fun setPasswordNotMatchError()

    fun setShortPasswordError()

    fun showUnknownErrorToast(error: String)

    fun openMainActivity()

    fun showProgress()

    fun hideProgress()

}