package com.bignerdranch.android.androidwithkotlin.utils

import android.util.Log
import timber.log.Timber

object ReleaseTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) return
    }
}
