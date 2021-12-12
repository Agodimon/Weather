package com.bignerdranch.android.androidwithkotlin.utils

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

interface Preferences {

    fun save(key: String, text: String)

    fun save(key: String, value: Int)

    fun save(key: String, value: Long)

    fun save(key: String, status: Boolean)

    fun save(key: String, status: Set<String>)

    fun getString(key: String, defaultValue: String): String?

    fun getInt(key: String, defaultValue: Int): Int

    fun getLong(key: String, defaultValue: Long): Long

    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun getStringSet(key: String, defaultValue: Set<String>): Set<String>?

    fun removeValue(key: String)

    fun clearPreferences()

    companion object {
        const val PREF_KEY_IS_RUS_LOCATION = "pref_key_is_rus_weather_location"
    }
}

@Singleton
class PreferencesImpl @Inject constructor(private val context: Context) : Preferences {

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun save(key: String, text: String) =
        preferences.edit().putString(key, text).apply()

    override fun save(key: String, value: Int) =
        preferences.edit().putInt(key, value).apply()

    override fun save(key: String, value: Long) =
        preferences.edit().putLong(key, value).apply()

    override fun save(key: String, status: Boolean) =
        preferences.edit().putBoolean(key, status).apply()

    override fun save(key: String, status: Set<String>) =
        preferences.edit().putStringSet(key, status).apply()

    override fun getString(key: String, defaultValue: String): String? =
        preferences.getString(key, defaultValue)

    override fun getInt(key: String, defaultValue: Int): Int =
        preferences.getInt(key, defaultValue)

    override fun getLong(key: String, defaultValue: Long): Long =
        preferences.getLong(key, defaultValue)

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        preferences.getBoolean(key, defaultValue)

    override fun getStringSet(key: String, defaultValue: Set<String>): Set<String>? =
        preferences.getStringSet(key, defaultValue)

    override fun removeValue(key: String) = preferences.edit().remove(key).apply()

    override fun clearPreferences() = preferences.edit().clear().apply()

    companion object {
        private const val PREFERENCES_NAME = "app_prefs"
    }
}
