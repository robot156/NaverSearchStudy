package com.poly.test.naversearchstudy.retrofit

import android.util.Log
import com.google.gson.JsonElement
import com.poly.test.naversearchstudy.model.BookData
import com.poly.test.naversearchstudy.utils.API
import com.poly.test.naversearchstudy.utils.API.CLIENT_ID
import com.poly.test.naversearchstudy.utils.API.CLIENT_KEY
import com.poly.test.naversearchstudy.utils.ExtensionFunc.formedPrice
import com.poly.test.naversearchstudy.utils.RESPONSE_STATUS
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }

    private val iRetrofit: NaverBookAPI? = RetrofitClient.getClient()?.create(NaverBookAPI::class.java)


    fun searchBooks(searchText: String?, completion: (RESPONSE_STATUS, ArrayList<BookData>?) -> Unit) {

        val search: String = searchText ?: ""

        val call = iRetrofit?.getSearchBook(CLIENT_ID, CLIENT_KEY, search, "100") ?: return

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
                                    var price = resultItemObject.get("price").asString

                                    title = title.replace("<b>", "")
                                            .replace("</b>", "")
                                            .replace("#", "")
                                            .replace("&", "")
                                            .replace("lt", "")
                                            .replace("x0D", "")

                                    description = description.replace("<b>", "")
                                            .replace("</b>", "")
                                            .replace("#", "")
                                            .replace("&", "")
                                            .replace(";", "")
                                            .replace("x0D", "")

                                    author = author.replace("<b>", "")
                                            .replace("</b>", "")
                                            .replace("#", "")
                                            .replace("&", "")
                                            .replace(";", "")
                                            .replace("x0D", "")


                                    var priceText = "unknown"

                                    if(price != "") {
                                        price = price.toFloat().toInt().toString()
                                        priceText = price.toInt().formedPrice()
                                    }

                                    if(author == ""){
                                        author = "작자 미상"
                                    }

                                    val searchItem = BookData(
                                            author = author,
                                            description = description,
                                            title = title,
                                            image = image,
                                            price = priceText
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