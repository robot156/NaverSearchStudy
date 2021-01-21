//package com.poly.test.naversearchstudy.retrofit
//
//import com.poly.test.naversearchstudy.model.ResultGetSearchData
//import com.poly.test.naversearchstudy.utils.API
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Call
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//import retrofit2.http.Header
//import retrofit2.http.Query
//
//interface NaverAPI {
//
//    @GET(API.SEARCH_BOOK)
//    fun getSearchBook(
//            @Header("X-naver-Client-Id") clientId : String,
//            @Header("X-naver-Client-Secret") clientSecret: String,
//            @Query("query") query: String,
//            @Query("display") display: Int? = null,
//            @Query("start") start: Int? = null
//    ) : Call<ResultGetSearchData>
//
//
//    companion object {
//
//        private const val BASE_URL_NAVER_API = "https://openapi.naver.com/"
//        private const val CLIENT_ID = "gSZg2bvyDyX5ZLYwdkjl"
//        private const val CLIENT_SECRET = "qAMJelGnEz"
//
//
//        fun create(): NaverAPI {
//
//            val httpLoggingInterceptor = HttpLoggingInterceptor()
//            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//            val headInterceptor = Interceptor {
//                val request = it.request()
//                        .newBuilder()
//                        .addHeader("X-Naver-Client-Id", CLIENT_ID)
//                        .addHeader("X-naver-Client", CLIENT_SECRET)
//                        .build()
//
//                return@Interceptor it.proceed(request)
//            }
//
//            val client = OkHttpClient.Builder()
//                    .addInterceptor(headInterceptor)
//                    .addInterceptor(httpLoggingInterceptor)
//                    .build()
//
//            return Retrofit.Builder()
//                    .baseUrl(BASE_URL_NAVER_API)
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                    .create(NaverAPI::class.java)
//
//        }
//
//
//    }
//
//
//}