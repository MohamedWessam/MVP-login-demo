package com.wessam.bloodbank.ui.splash

import com.google.firebase.auth.FirebaseAuth

class SplashInteractorImpl : SplashInteractor {

    override fun getCurrentUser() = FirebaseAuth.getInstance().currentUser

}