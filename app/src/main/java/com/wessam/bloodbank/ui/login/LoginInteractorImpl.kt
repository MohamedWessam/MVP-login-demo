package com.wessam.bloodbank.ui.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.wessam.bloodbank.utils.isEmail

class LoginInteractorImpl : LoginInteractor {

    override fun login(email: String, password: String, listener: OnLoginFinishedListener) {
        when {
            email.isEmpty() -> listener.onEmailEmptyError()
            !email.isEmail() -> listener.onEmailWrongError()
            password.isEmpty() -> listener.onPasswordEmptyError()
            else -> firebaseAuth(email, password, listener)
        }
    }

    override fun sendPasswordResetEmail(email: String, listener: OnPasswordResetEmailListener) {
        val auth = FirebaseAuth.getInstance()

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    listener.onEmailSent()
                } else {
                    listener.onResetPasswordUnknownError(task.exception?.message.toString())
                }
            }
    }

    private fun firebaseAuth(email: String, password: String, listener: OnLoginFinishedListener) {
        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    listener.onSuccess()
                } else {
                    listener.onUnknownError(task.exception?.message.toString())
                }
            }
    }

}