package com.talentomobile.marvel.data.utils

import com.talentomobile.marvel.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object {
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val OFFSET = "0"
        const val LIMIT = 100
        const val INVALID_URL_FORMAT = "http://"
        const val VALID_URL_FORMAT = "https://"
        const val FIXED_IMAGE_WIDTH = 320
        const val FIXED_IMAGE_HEIGHT = 480

        fun hash(): String {
            val input = "$timeStamp${BuildConfig.MY_PRIVATE_KEY}${BuildConfig.API_KEY}"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}