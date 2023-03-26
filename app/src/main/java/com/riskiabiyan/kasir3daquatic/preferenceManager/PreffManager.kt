package com.riskiabiyan.kasir3daquatic.preferenceManager

import android.content.Context
import android.content.SharedPreferences

class PreffManager(context: Context) {

    val PRIVATE_MODE = 0

    private val PREF_NAME = "SharedPreference"
    private val IS_LOGIN = "is_login"

    val pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    val editor : SharedPreferences.Editor? = pref?.edit()

    fun setLogin(isLogin : Boolean){
        editor?.putBoolean(IS_LOGIN, isLogin)
        editor?.commit()
    }

    fun setUsername(username : String?){
        editor?.putString("username", username)
        editor?.commit()
    }

    fun setID(id_user : String?){
        editor?.putString("id_user", id_user)
        editor?.commit()
    }

    fun getID() : String?{
        return pref?.getString("id_user", "")
    }

    fun isLogin() : Boolean?{
        return pref?.getBoolean(IS_LOGIN, false)
    }

    fun getUsername() : String?{
        return pref?.getString("username", "")
    }

    fun removeData(){
        editor?.clear()
        editor?.commit()
    }

}