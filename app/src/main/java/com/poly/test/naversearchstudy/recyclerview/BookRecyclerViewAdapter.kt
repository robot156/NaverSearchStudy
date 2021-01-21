package com.poly.test.naversearchstudy.recyclerview

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.poly.test.naversearchstudy.R
import com.poly.test.naversearchstudy.databinding.LayoutBookItemBinding
import com.poly.test.naversearchstudy.model.BookData

class BookRecyclerViewAdapter(val context: Activity, val bookList: ArrayList<BookData>) :
    RecyclerView.Adapter<BookRecyclerViewAdapter.BookItemViewHolder>() {


    lateinit var binding: LayoutBookItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {

        binding = LayoutBookItemBinding.inflate(LayoutInflater.from(context))

       // val view = LayoutInflater.from(context).inflate(R.layout.layout_book_item, parent, false)

        binding = LayoutBookItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return BookItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {

        holder.bindView(bookList[position])
    }

    override fun getItemCount(): Int {

        return bookList.size
    }

    inner class BookItemViewHolder(binding: LayoutBookItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var view = binding.root
        var bookImg = binding.bookImg
        var bookName = binding.bookName
        var bookAuthor = binding.bookAuthor

        fun bindView(bookData: BookData) {

            bookName.text = bookData.title
            bookAuthor.text =bookData.author

            Glide.with(context).load(bookData.image)
                .placeholder(R.drawable.ic_baseline_insert_photo_24)
                .into(bookImg)



        }

    }

}