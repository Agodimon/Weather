package com.bignerdranch.android.androidwithkotlin.presentation.base

import android.os.Bundle
import android.os.Parcelable

abstract class BaseArgument : Parcelable {

    fun toBundle(): Bundle = Bundle().apply { putParcelable(ARG_KEY, this) }

    companion object {
        const val ARG_KEY = "arg_key"
    }
}
