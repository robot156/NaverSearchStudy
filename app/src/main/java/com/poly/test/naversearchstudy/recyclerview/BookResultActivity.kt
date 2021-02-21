package com.poly.test.naversearchstudy.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.poly.test.naversearchstudy.BaseActivity
import com.poly.test.naversearchstudy.R
import com.poly.test.naversearchstudy.databinding.ActivityBookResultBinding
import com.poly.test.naversearchstudy.model.BookData

class BookResultActivity : BaseActivity<ActivityBookResultBinding>(R.layout.activity_book_result) {



    private var bookList = ArrayList<BookData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val bundle = intent.getBundleExtra("array_bundle")

        val searchText: String? = intent.getStringExtra("search_text")


        mBinding.topAppBar.title = searchText.toString()
        setSupportActionBar(mBinding.topAppBar)

        bookList = bundle?.getSerializable("book_array_list") as ArrayList<BookData>

        mBinding.myBookRecyclerview.adapter = BookRecyclerViewAdapter(this, bookList)
        mBinding.myBookRecyclerview.layoutManager = LinearLayoutManager(this)

        mBinding.invalidateAll()

    }
}