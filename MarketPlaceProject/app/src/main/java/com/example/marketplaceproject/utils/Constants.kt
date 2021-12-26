package com.example.marketplaceproject.utils

class Constants {
    companion object {
        val EMAIL_REGEX_PATTERN = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
        val EMAIL = "email"
        val REGISTRATIONFEEDBACK = "registrationFeedBack"
        val PASSWORDCHANGEFEEDBACK = "passwordChangeFeedBack"
        val SHAREDPREF = "sharedpref"
        val TOKEN = "token"
        val USERNAME = "username"
        val RATING = "rating"
        val AMOUNTTYPE = "amountType"
        val PRICETYPE = "pryceType"
        val PRODUCTID = "productId"
        val ISACTIVE = "isActive"
        val PRICEPERUNIT = "pricePerUnit"
        val UNITS = "units"
        val DESCRIPTION = "description"
        val TITLE = "title"
    }
}