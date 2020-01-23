package com.wessam.bloodbank.ui.signup

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.wessam.bloodbank.utils.isEmail

class SignUpInteractorImpl : SignUpInteractor {

    override fun createUser(email: String, password: String, confirmPassword: String, listener: OnSignUpFinishedListener) {
        when{
            email.isEmpty() -> listener.onEmailEmptyError()
            !email.isEmail() -> listener.onEmailWrongError()
            password.isEmpty() -> listener.onPasswordEmptyError()
            confirmPassword.isEmpty() -> listener.onConfirmPasswordEmptyError()
            password.length < 6 -> listener.onPasswordShortError()
            password != confirmPassword -> listener.onPasswordNotMatchError()
            else ->  createUserWithEmailAndPassword(email, password, listener)
        }
    }

    private fun createUserWithEmailAndPassword(email: String, password: String, listener: OnSignUpFinishedListener) {
        val mAuth = FirebaseAuth.getInstance()

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    listener.onSuccess()
                } else {
                    listener.onUnknownError(task.exception?.message.toString())
                }
            }
    }

}