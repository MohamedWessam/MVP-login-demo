package com.wessam.bloodbank.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Placeholder
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

/**
 * show toast for Context (from string.xml).
 */
fun Context?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) =
    this?.let { Toast.makeText(it, textId, duration).show() }

/**
 * show string toast for Context.
 */
fun Context?.toast(textId: String, duration: Int = Toast.LENGTH_LONG) =
    this?.let { Toast.makeText(it, textId, duration).show() }


/**
 * show a snackbar (Length = Long)
 */
fun View.showSnackbar(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, messageRes, length).show()
}

/**
 * Show the view  (visibility = View.VISIBLE)
 */
fun View.visible(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Hide the view. (visibility = View.INVISIBLE)
 */
fun View.invisible(): View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Remove the view (visibility = View.GONE)
 */
fun View.gone(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

/**
 * startActivity for Context.
 */
inline fun <reified T : Activity> Context?.startActivity() =
    this?.startActivity(Intent(this, T::class.java))

/**
 * startActivity with Animation for Context.
 */
inline fun <reified T : Activity> Context.startActivityWithAnimation(
    enterResId: Int = 0,
    exitResId: Int = 0
) {
    val intent = Intent(this, T::class.java)
    val bundle = ActivityOptionsCompat.makeCustomAnimation(this, enterResId, exitResId).toBundle()
    ContextCompat.startActivity(this, intent, bundle)
}

/**
 * startActivity with Animation for Context.
 */
inline fun <reified T : Activity> Context.startActivityWithAnimation(
    enterResId: Int = 0,
    exitResId: Int = 0,
    intentBody: Intent.() -> Unit
) {
    val intent = Intent(this, T::class.java)
    intent.intentBody()
    val bundle = ActivityOptionsCompat.makeCustomAnimation(this, enterResId, exitResId).toBundle()
    ContextCompat.startActivity(this, intent, bundle)
}



/**
 * Setup actionbar
 */
fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run {
        action()
    }
}


/////////////////*******************************////////////////////////
/**
 * Extension method to check if String is Email.
 */
fun String.isEmail(): Boolean {
    val p = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex()
    return matches(p)
}

/**
 * Extension method to check if String is Phone Number.
 */
fun String.isPhone(): Boolean {
    val p = "^1([34578])\\d{9}\$".toRegex()
    return matches(p)
}

/**
 * Extension method to check if String is Number.
 */
fun String.isNumeric(): Boolean {
    val p = "^[0-9]+$".toRegex()
    return matches(p)
}

/**
 * Extension method to load imageView from url.
 */
fun ImageView.loadFromUrl(imageUrl: String, @DrawableRes placeholderId: Int? = null) {
    if (placeholderId != null)
        Picasso.get().load(imageUrl).placeholder(placeholderId).into(this)
    else
        Picasso.get().load(imageUrl).into(this)
}

/**
 * Extension method to write preferences.
 */
inline fun SharedPreferences.edit(preferApply: Boolean = false, f: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.f()
    if (preferApply) editor.apply() else editor.commit()
}


