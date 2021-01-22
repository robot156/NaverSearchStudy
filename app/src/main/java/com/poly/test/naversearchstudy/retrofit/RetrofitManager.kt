package com.poly.test.naversearchstudy.retrofit

import android.util.Log
import com.google.gson.JsonElement
import com.poly.test.naversearchstudy.model.BookData
import com.poly.test.naversearchstudy.utils.API
import com.poly.test.naversearchstudy.utils.API.CLIENT_ID
import com.poly.test.naversearchstudy.utils.API.CLIENT_KEY
import com.poly.test.naversearchstudy.utils.RESPONSE_STATUS
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }


    private val iRetrofit: NaverBookAPI? = RetrofitClient.getClient(API.BASE_URL)?.create(NaverBookAPI::class.java)


    fun searchBooks(searchText: String?, completion: (RESPONSE_STATUS, ArrayList<BookData>?) -> Unit) {

        val search: String = searchText ?: ""

        val call = iRetrofit?.getSearchBook(CLIENT_ID, CLIENT_KEY, search) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {

                Log.d("로그", "onResponse call/ response : ${response.raw()}")

                when (response.code()) {

                    200 -> {

                        response.body()?.let {

                            val parsedSearchDataArray = ArrayList<BookData>()
                            val body = it.asJsonObject
                            val total = body.get("total").asInt
                            Log.d("로그", "retrofitManager - total: $total")

                            if (total == 0) {

                                completion(RESPONSE_STATUS.NO_CONTENT, null)

                            } else {
                                val results = body.getAsJsonArray("items")
                                results.forEach { resultItem ->

                                    val resultItemObject = resultItem.asJsonObject
                                    val image = resultItemObject.get("image").asString
                                    var title = resultItemObject.get("title").asString
                                    var description = resultItemObject.get("description").asString
                                    var author = resultItemObject.get("author").asString


                                    title = title.replace("<b>", "").replace("</b>", "").replace("#", "").replace("&", "").replace(";", "")
                                    description = description.replace("<b>", "").replace("</b>", "").replace("#", "").replace("&", "").replace(";", "")
                                    author = author.replace("<b>", "").replace("</b>", "").replace("#", "").replace("&", "").replace(";", "")
                                    val searchItem = BookData(
                                            author = author,
                                            description = description,
                                            title = title,
                                            image = image
                                    )

                                    parsedSearchDataArray.add(searchItem)

                                }
                                completion(RESPONSE_STATUS.OKAY, parsedSearchDataArray)

                            }
                        }

                    }

                }

            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {

                completion(RESPONSE_STATUS.FAIL, null)

                Log.d("로그", "onFailure call $t")

            }
        })

    }


}