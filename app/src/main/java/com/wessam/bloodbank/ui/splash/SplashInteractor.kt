package com.wessam.bloodbank.ui.splash

import com.google.firebase.auth.FirebaseUser

interface SplashInteractor {

    fun getCurrentUser(): FirebaseUser?

}