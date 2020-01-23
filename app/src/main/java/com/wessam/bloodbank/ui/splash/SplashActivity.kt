package com.wessam.bloodbank.ui.splash

import android.os.Handler
import android.view.animation.AnimationUtils
import com.wessam.bloodbank.ui.main.MainActivity
import com.wessam.bloodbank.R
import com.wessam.bloodbank.base.ParentActivity
import com.wessam.bloodbank.ui.login.LoginActivity
import com.wessam.bloodbank.utils.startActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : ParentActivity(), SplashView {

    lateinit var presenter: SplashPresenter

    override fun initializeComponents() {
        presenter = SplashPresenterImpl(this, SplashInteractorImpl())

        animateImageView()
        animateTextView()

        Handler().postDelayed({
            presenter.decideNextActivity()
        }, 3500)
    }

    override fun animateImageView() {
        splash_image.alpha = 0f
        splash_image.animate().apply {
            alpha(1f)
            startDelay = 900
            duration = 600
        }.start()
    }

    override fun animateTextView() {
        splash_text.animation = AnimationUtils.loadAnimation(this, R.anim.from_bottom)
    }

    override fun getLayoutResource() = R.layout.activity_splash

    override fun openMainActivity() {
        startActivity<MainActivity>()
        finish()
    }

    override fun openLoginActivity() {
        startActivity<LoginActivity>()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}