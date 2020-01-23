package com.wessam.bloodbank.ui.splash

class SplashPresenterImpl(var view: SplashView?, var interactor: SplashInteractor) : SplashPresenter {

    override fun decideNextActivity() {
        if (interactor.getCurrentUser() != null) view?.openMainActivity()
        else view?.openLoginActivity()
    }

    override fun onDestroy() {
        view = null
    }

}