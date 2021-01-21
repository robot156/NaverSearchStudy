package com.poly.test.naversearchstudy.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.poly.test.naversearchstudy.R
import com.poly.test.naversearchstudy.databinding.ActivityBookResultBinding
import com.poly.test.naversearchstudy.model.BookData

class BookResultActivity : AppCompatActivity() {


    lateinit var binding : ActivityBookResultBinding

    private var bookList = ArrayList<BookData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookResultBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val bundle = intent.getBundleExtra("array_bundle")

        val searchText: String? = intent.getStringExtra("search_text")


        binding.topAppBar.title = searchText.toString()
        setSupportActionBar(binding.topAppBar)

        bookList = bundle?.getSerializable("book_array_list") as ArrayList<BookData>

        binding.myBookRecyclerview.adapter = BookRecyclerViewAdapter(this, bookList)
        binding.myBookRecyclerview.layoutManager = LinearLayoutManager(this)




    }
}