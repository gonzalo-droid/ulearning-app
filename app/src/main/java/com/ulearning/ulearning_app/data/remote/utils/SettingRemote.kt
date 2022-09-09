package com.ulearning.ulearning_app.data.remote.utils

import com.google.gson.annotations.SerializedName


class SettingRemote {
    companion object {

        /**
         * Params
         *
         */


        const val PASSWORD = "password"

        /**
         *  Remote
         */
        const val HEADER_AUTH_KEY = "AUTH-KEY-PEX"
        const val HEADER_AUTH_VALUE = "Pruebatoken"

        const val BEARER = "Bearer"
        const val AUTHORIZATION = "Authorization"
        const val CONTENT_TYPE_JSON = "application/json"
        const val ACCEPT_JSON = "application/json"
        const val TEXT_PLAIN = "text/plain"
        const val IMAGE_JPG = "image/jpg"
        const val REGION_APP = "es_PE"
        const val SYMBOL_MILLIONS = ","

        const val COURSE_ID = "course_id"
    }
}