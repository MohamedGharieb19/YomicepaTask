package com.gharieb.yomicepatask.core.common.sharedPreference

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor( val sharedPreferences: SharedPreferences) {

    fun <T> put( key: String, `object`: T) {

        val jsonString = GsonBuilder().create().toJson(`object`)

        sharedPreferences.edit().putString(key, jsonString).apply()
    }

    inline fun <reified T> get(key: String): T? {
        val value = sharedPreferences.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}