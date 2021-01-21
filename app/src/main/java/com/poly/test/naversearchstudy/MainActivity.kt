package com.poly.test.naversearchstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.JsonElement
import com.poly.test.naversearchstudy.databinding.ActivityMainBinding
import com.poly.test.naversearchstudy.recyclerview.BookResultActivity
import com.poly.test.naversearchstudy.retrofit.RetrofitManager
import com.poly.test.naversearchstudy.utils.API
import com.poly.test.naversearchstudy.utils.RESPONSE_STATUS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        mBinding.searchButton.setOnClickListener {

            val searchData = mBinding.searchEdit.text

            RetrofitManager.instance.searchBooks(searchText = searchData.toString(), completion = {
                responseStatus, responseDataArrayList ->

                when(responseStatus) {

                    RESPONSE_STATUS.OKAY -> {
                        Log.d("로그","성공 ${responseDataArrayList?.size}")
                        Log.d("로그","작가 ${responseDataArrayList?.get(0)?.author}")
                        Log.d("로그","설명 ${responseDataArrayList?.get(0)?.description}")
                        Log.d("로그","타이틀 ${responseDataArrayList?.get(0)?.title}")
                        Log.d("로그","이미지 ${responseDataArrayList?.get(0)?.image}")
                        val intent = Intent(this, BookResultActivity::class.java)
                        val bundle = Bundle()
                        bundle.putSerializable("book_array_list", responseDataArrayList)
                        intent.putExtra("array_bundle", bundle)
                        intent.putExtra("search_text",searchData.toString())
                        startActivity(intent)
                    }

                    RESPONSE_STATUS.FAIL -> {
                        Log.d("로그","실패")

                    }

                    RESPONSE_STATUS.NO_CONTENT -> {
                        Log.d("로그","데이터없음")

                    }

                }

            })

        }



    }
}