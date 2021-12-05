package com.bignerdranch.android.androidwithkotlin

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar


fun View.showSnackBar(
    text: String,
    actionText: String,
    action: ((View) -> Unit)? = null,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    val ourSnackBar = Snackbar.make(this, text, length)
    action?.let {
        ourSnackBar.setAction(actionText, it)
    }
    ourSnackBar.show()
}
