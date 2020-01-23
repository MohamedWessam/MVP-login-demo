package com.wessam.bloodbank.ui.signup

import com.thekhaeng.pushdownanim.PushDownAnim
import com.wessam.bloodbank.R
import com.wessam.bloodbank.base.ParentActivity
import com.wessam.bloodbank.ui.main.MainActivity
import com.wessam.bloodbank.utils.startActivity
import com.wessam.bloodbank.utils.toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : ParentActivity(), SignUpView {

    private lateinit var presenter: SignUpPresenter

    override fun getLayoutResource() = R.layout.activity_sign_up

    override fun initializeComponents() {
        PushDownAnim.setPushDownAnimTo(sign_up_button)

        sign_up_progress_bar.hide()

        presenter = SignUpPresenterImpl(this, SignUpInteractorImpl())

        sign_up_button.setOnClickListener { validateCredentials() }
    }

    private fun validateCredentials() {
        presenter.validateCredentials(
            email = sign_up_email.text.toString().trim(),
            password = sign_up_password.text.toString().trim(),
            confirmPassword = confirm_password.text.toString().trim()
        )
    }

    override fun setEmailEmptyError() {
        sign_up_email.error = getString(R.string.required_field)
    }

    override fun setEmailWrongError() {
        sign_up_email.error = getString(R.string.wrong_email)
    }

    override fun setPasswordEmptyError() {
        sign_up_password.error = getString(R.string.required_field)
    }

    override fun setConfirmPasswordEmptyError() {
        confirm_password.error = getString(R.string.required_field)
    }

    override fun setPasswordNotMatchError() {
        confirm_password.error = getString(R.string.password_not_match)
    }

    override fun setShortPasswordError() {
        sign_up_password.error = getString(R.string.short_password)
    }

    override fun showUnknownErrorToast(error: String) {
        toast(error)
    }

    override fun openMainActivity() {
        startActivity<MainActivity>()
    }

    override fun showProgress() {
        sign_up_progress_bar.show()
    }

    override fun hideProgress() {
        sign_up_progress_bar.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}
