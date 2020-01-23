package com.wessam.bloodbank.ui.signup

class SignUpPresenterImpl(var view: SignUpView?, var interactor: SignUpInteractor) :
    SignUpPresenter, OnSignUpFinishedListener {

    override fun validateCredentials(email: String, password: String, confirmPassword: String) {
        view?.showProgress()
        interactor.createUser(email, password, confirmPassword, this)
    }

    override fun onDestroy() {
        view = null
    }

    override fun onEmailEmptyError() {
        view?.apply {
            hideProgress()
            setEmailEmptyError()
        }
    }

    override fun onEmailWrongError() {
        view?.apply {
            hideProgress()
            setEmailWrongError()
        }
    }

    override fun onPasswordEmptyError() {
        view?.apply {
            hideProgress()
            setPasswordEmptyError()
        }
    }

    override fun onConfirmPasswordEmptyError() {
        view?.apply {
            hideProgress()
            setConfirmPasswordEmptyError()
        }
    }

    override fun onPasswordNotMatchError() {
        view?.apply {
            hideProgress()
            setPasswordNotMatchError()
        }
    }

    override fun onPasswordShortError() {
        view?.apply {
            hideProgress()
            setShortPasswordError()
        }
    }

    override fun onSuccess() {
        view?.apply {
            hideProgress()
            openMainActivity()
        }
    }

    override fun onUnknownError(error: String) {
        view?.apply {
            hideProgress()
            showUnknownErrorToast(error)
        }
    }

}