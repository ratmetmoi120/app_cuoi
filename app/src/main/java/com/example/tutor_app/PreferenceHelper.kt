package com.example.tutor_app

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(mainActivity: Res) {
    private val INTRO = "intro"
    private val NAME = "name"
    private val HOBBY = "hobby"
    private var app_prefs: SharedPreferences? = null
    private var context: Context? = null

    fun PreferenceHelper(context: Context) {
        app_prefs = context.getSharedPreferences(
            "shared",
            Context.MODE_PRIVATE
        )
        this.context = context
    }

    fun putIsLogin(loginorout: Boolean) {
        try {
            val edit = app_prefs!!.edit()
            edit.putBoolean(INTRO, loginorout)
            edit.commit()
        }
        catch (e: Exception ){
            e.printStackTrace()
        }

    }

    fun getIsLogin(): Boolean = app_prefs!!.getBoolean(INTRO, false)

    fun putName(loginorout: String?) {
        val edit = app_prefs!!.edit()
        edit.putString(NAME, loginorout)
        edit.commit()
    }

    fun getName(): String? {
        return app_prefs!!.getString(NAME, "")
    }

    fun putHobby(loginorout: String?) {
        val edit = app_prefs!!.edit()
        edit.putString(HOBBY, loginorout)
        edit.commit()
    }

    fun getHobby(): String? {
        return app_prefs!!.getString(HOBBY, "")
    }
}