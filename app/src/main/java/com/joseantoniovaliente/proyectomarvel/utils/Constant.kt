package com.joseantoniovaliente.proyectomarvel.utils

import android.content.Context
import android.net.ConnectivityManager
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp


class Constant {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "2d17f92ccd115e7ac85a5a7fbc236550"
        const val PRIVATE_KEY = "d269ec13a5ac2a60975e9eefbc4062d8dd81c10e"
        const val limit = "50"


        fun hash(): String {
            val input = "$ts$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting
        }
    }


}