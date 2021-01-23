package com.poly.test.naversearchstudy.retrofit

import android.util.Log
import com.poly.test.naversearchstudy.utils.API
import com.poly.test.naversearchstudy.utils.API.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofitClient : Retrofit? = null

    fun getClient() : Retrofit? {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val headInterceptor = Interceptor {
            val request = it.request()
                    .newBuilder()
                    .addHeader("X-Naver-Client-Id", API.CLIENT_ID)
                    .addHeader("X-naver-Client", API.CLIENT_KEY)
                    .build()

            return@Interceptor it.proceed(request)
        }

        val client = OkHttpClient.Builder()
                .addInterceptor(headInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()


        if(retrofitClient == null) {

            retrofitClient = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

        }


        return retrofitClient

    }


}