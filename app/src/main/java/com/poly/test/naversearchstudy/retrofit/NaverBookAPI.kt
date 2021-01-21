package com.poly.test.naversearchstudy.retrofit

import com.google.gson.JsonElement
import com.poly.test.naversearchstudy.utils.API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverBookAPI {

    @GET(API.SEARCH_BOOK)
    fun getSearchBook(
            @Header("X-naver-Client-Id") clientId : String,
            @Header("X-naver-Client-Secret") clientSecret: String,
            @Query("query") query: String
//            @Query("display") display: Int? = null,
//            @Query("start") start: Int? = null
    ) : Call<JsonElement>

}