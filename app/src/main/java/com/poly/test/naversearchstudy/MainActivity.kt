package com.poly.test.naversearchstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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

    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        mBinding.searchButton.setOnClickListener {

            progressUiShow()

            val searchData = mBinding.searchEdit.text

            RetrofitManager.instance.searchBooks(searchText = searchData.toString(), completion = { responseStatus, responseDataArrayList ->

                when (responseStatus) {

                    RESPONSE_STATUS.OKAY -> {

                        progressUiHidden()
                        val intent = Intent(this, BookResultActivity::class.java)
                        val bundle = Bundle()
                        bundle.putSerializable("book_array_list", responseDataArrayList)
                        intent.putExtra("array_bundle", bundle)
                        intent.putExtra("search_text", searchData.toString())
                        startActivity(intent)
                    }

                    RESPONSE_STATUS.FAIL -> {

                        Log.d("로그", "실패")
                        progressUiHidden()
                        Toast.makeText(this, "서버 에러", Toast.LENGTH_SHORT).show()
                    }

                    RESPONSE_STATUS.NO_CONTENT -> {

                        Log.d("로그", "데이터없음")
                        progressUiHidden()
                        Toast.makeText(this, "찾는 책이 없습니다", Toast.LENGTH_SHORT).show()
                    }

                }

            })

        }
    }


    private fun progressUiShow() {
        mBinding.bookLoadingLayout.visibility = View.VISIBLE
        mBinding.bookProgressbar.visibility = View.VISIBLE
        mBinding.searchButton.isEnabled = false
    }

    private fun progressUiHidden() {
        mBinding.bookLoadingLayout.visibility = View.GONE
        mBinding.bookProgressbar.visibility = View.GONE
        mBinding.searchButton.isEnabled = true
    }

}