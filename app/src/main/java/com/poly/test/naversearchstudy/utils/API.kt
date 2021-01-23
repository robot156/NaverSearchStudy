package com.poly.test.naversearchstudy.utils


object API {

    const val BASE_URL: String = "https://openapi.naver.com/"
    const val CLIENT_ID: String = "" // your ID
    const val CLIENT_KEY: String = "" // your key
    const val SEARCH_BOOK: String = "v1/search/book?query"

}

enum class RESPONSE_STATUS {

    OKAY,
    FAIL,
    NO_CONTENT

}