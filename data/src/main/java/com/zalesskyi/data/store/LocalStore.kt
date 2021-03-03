package com.zalesskyi.data.store

import android.annotation.SuppressLint
import android.content.Context
import javax.inject.Inject

@SuppressLint("ApplySharedPref")
class LocalStore
@Inject
constructor(context: Context) {

    companion object {

        private const val PREFS = "PREFS"
        private const val EMAIL_KEY = "email"
    }

    private val preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun setEmail(email: String?) {
        preferences.edit().putString(EMAIL_KEY, email).commit()
    }

    fun getEmail() =
        preferences.getString(EMAIL_KEY, null)
}