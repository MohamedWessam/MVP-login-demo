package com.wessam.bloodbank.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.wessam.bloodbank.utils.SharedPreferencesManager

abstract class ParentActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private lateinit var mActivity: AppCompatActivity

    lateinit var mSharedPreferences: SharedPreferencesManager

    protected var toolbarTitle: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        mContext = this
        mActivity = this

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContentView(getLayoutResource())

        mSharedPreferences = SharedPreferencesManager(mContext)

        initializeComponents()


    }

    /**
     * hide keyboard after typing
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * initialize components
     */
    protected abstract fun initializeComponents()


    /**
     * get layout resource
     */
    protected abstract fun getLayoutResource(): Int

}