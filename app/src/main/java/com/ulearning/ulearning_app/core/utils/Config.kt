package com.ulearning.ulearning_app.core.utils

object Config {

    /**
     * DataStore
     */

    const val NAME_DATA_STORE = "pex_preferences"

    /**
     * Web View
     */
    const val URL_WEB_VIEW = "https://miportal.pex.com.pe/pex21/afiliacion_prepago/"

    /**
     * Put extra
     */
    const val DATA_VALIDATED_DOCUMENT_PUT = "data_validated_document_put"

    const val DATE_FORMAT_ONE = "EEEE"
    const val DATE_FORMAT_TWO = "yyyy-MM-dd"
    const val DATE_FORMAT_THREE = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMAT_FOUR = "dd/MM/yy"
    const val DATE_FORMAT_FIVE = "hh:mm:ss a"
    const val DATE_FORMAT_SIX = "dd MMM"
    const val DATE_FORMAT_SEVEN = "hh:mm a"
    const val DATE_FORMAT_EIGHT = "MMMM yyyy"
    const val DATE_FORMAT_NINE = "MMMM"
    const val DATE_FORMAT_TEN = "yyyy"
    const val DATE_FORMAT_ELEVEN = "dd MMM yyyy"
    const val DATE_FORMAT_TWELVE = "dd 'de' MMMM 'de' yyyy','hh:mm"
    const val DATE_FORMAT_THIRTEEN = "dd 'de' MMMM','hh:mm"
    const val DATE_FORMAT_FOURTEEN = "dd 'de' MMMM','yyyy"
    const val DATE_FORMAT_FIFTEEN = "dd/MM/yyyy"
    const val DATE_FORMAT_SIXTEEN = "dd / MMM / yyyy"
    const val DATE_FORMAT_EIGHTEEN = "yyyy-MM-dd'T'HH:mm:ss"
    const val DATE_FORMAT_NINETEEN = "dd/MM/yyyy hh:mm a"
    const val DATE_FORMAT_THIRTY = "dd/MM HH:mm"

    const val DECIMAL_FORMAT_ONE = "S/ ###,#00.00"
    const val DECIMAL_FORMAT_TWO = "00"
    const val DECIMAL_FORMAT_THREE = "00.00"
    const val DECIMAL_FORMAT_FOUR = "###,##0.00"

    const val CONSUMPTION_FILTER_PUT = "consumptionFilter"

    const val EMAIL_REGEX =
        "[ _A-Za-z0-9-]+(\\.[ _A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})"
    const val ALPHA_NUMERIC_REGEX =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$"

    const val SUBSCRIPTION_PUT = "subscription"
    const val PERCENTAGE_PUT = "percentage"
    const val COURSE_ID_PUT = "courseId"
    const val PARAM_NAME_PUT = "paramName"
    const val LIST_USER_IDS_PUT = "listUserIds"
    const val MESSAGE_UUID_PUT = "uuidMessage"
    const val CONVERSATION_PUT = "conversation"
    const val COURSE_PUT = "course"
    const val COURSE_PACKAGE_ID_PUT = "coursePackageId"

    const val ROLE_TEACHER = "teacher"
    const val ROLE = "role"
    const val ROLE_STUDENT = "student"

    const val TYPE_MESSAGE = "tyMessage"
    const val MESSAGE_SUPPORT = "support"
    const val MESSAGE_COURSE = "course"

    /* 12 digits */
    val DEVICE_ID = "${android.os.Build.BOARD.length % 10}" +
        "${android.os.Build.DEVICE.length % 10}" +
        "${android.os.Build.DISPLAY.length % 10}" +
        "${android.os.Build.HOST.length % 10}" +
        "${android.os.Build.ID.length % 10}" +
        "${android.os.Build.MANUFACTURER.length % 10}" +
        "${android.os.Build.MODEL.length % 10}" +
        "${android.os.Build.PRODUCT.length % 10}" +
        "${android.os.Build.TAGS.length % 10}" +
        "${android.os.Build.TYPE.length % 10}" +
        "${android.os.Build.USER.length % 10}"
}
