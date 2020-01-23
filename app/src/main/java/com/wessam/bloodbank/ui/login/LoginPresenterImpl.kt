package com.wessam.bloodbank.ui.login

class LoginPresenterImpl(var view: LoginView?, var interactor: LoginInteractorImpl) :
    LoginPresenter, OnLoginFinishedListener, OnPasswordResetEmailListener {

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

    override fun onEmailSent() {
        view?.showEmailSentToast()
    }

    override fun onResetPasswordUnknownError(error: String) {
        view?.showUnknownErrorToast(error)
    }

    override fun validateCredentials(email: String, password: String) {
        view?.showProgress()
        interactor.login(email, password, this)
    }

    override fun sendPasswordResetEmail(email: String) {
        interactor.sendPasswordResetEmail(email, this)
    }

    override fun onDestroy() {
        view = null
    }

}