package com.wessam.bloodbank.ui.login

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import com.thekhaeng.pushdownanim.PushDownAnim
import com.wessam.bloodbank.R
import com.wessam.bloodbank.base.ParentActivity
import com.wessam.bloodbank.ui.main.MainActivity
import com.wessam.bloodbank.ui.signup.SignUpActivity
import com.wessam.bloodbank.utils.startActivity
import com.wessam.bloodbank.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.dialog_reset_password.*

class LoginActivity : ParentActivity(), LoginView, View.OnClickListener {

    private lateinit var presenter: LoginPresenter

    override fun getLayoutResource() = R.layout.activity_login

    override fun initializeComponents() {

        presenter = LoginPresenterImpl(this, LoginInteractorImpl())

        PushDownAnim.setPushDownAnimTo(login_button, navigate_to_sign_up_button, forget_password_button)

        login_progress_bar.hide()

        login_button.setOnClickListener(this)
        forget_password_button.setOnClickListener(this)
        navigate_to_sign_up_button.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view) {
            login_button -> validateCredentials()
            navigate_to_sign_up_button -> openSignUpActivity()
            forget_password_button -> showEditNameDialog()
        }
    }

    @SuppressLint("InflateParams")
    private fun showEditNameDialog() {
        val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_reset_password, null)
        val alertBuilder = AlertDialog.Builder(this).setView(dialog).show()
        val email = alertBuilder.findViewById<AppCompatEditText>(R.id.dialog_email)
        alertBuilder.reset_dialog_cancel_button.setOnClickListener { alertBuilder.dismiss() }
        alertBuilder.reset_dialog_ok_button.setOnClickListener {
            if (email?.text.toString().trim().isNotEmpty()) {
                presenter.sendPasswordResetEmail(email?.text.toString().trim())
                alertBuilder.dismiss()
            }
        }
    }

    private fun validateCredentials() {
        presenter.validateCredentials(
            login_email.text.toString().trim(),
            login_password.text.toString().trim()
        )
    }

    override fun openSignUpActivity() {
        startActivity<SignUpActivity>()
    }

    override fun openMainActivity() {
        startActivity<MainActivity>()
        finish()
    }

    override fun setEmailEmptyError() {
        login_email.error = getString(R.string.required_field)
    }

    override fun setEmailWrongError() {
        login_email.error = getString(R.string.wrong_email)
    }

    override fun setPasswordEmptyError() {
        login_password.error = getString(R.string.required_field)
    }

    override fun showUnknownErrorToast(error: String) {
        toast(error)
    }

    override fun showEmailSentToast() {
        toast(R.string.email_sent)
    }

    override fun showProgress() {
        login_progress_bar.show()
    }

    override fun hideProgress() {
        login_progress_bar.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}