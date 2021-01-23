package com.poly.test.naversearchstudy.recyclerview

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.Target
import com.poly.test.naversearchstudy.R
import com.poly.test.naversearchstudy.databinding.LayoutBookSearchItemBinding
import com.poly.test.naversearchstudy.model.BookData

class BookRecyclerViewAdapter(val context: Activity, val bookList: ArrayList<BookData>) :
        RecyclerView.Adapter<BookRecyclerViewAdapter.BookItemViewHolder>() {


    lateinit var binding: LayoutBookSearchItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {

        binding = LayoutBookSearchItemBinding.inflate(LayoutInflater.from(context))

        binding = LayoutBookSearchItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return BookItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {

        holder.bindView(bookList[position])
    }

    override fun getItemCount(): Int {

        return bookList.size
    }

    inner class BookItemViewHolder(binding: LayoutBookSearchItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val bookImg = binding.bookImg
        private val bookName = binding.bookName
        private val bookAuthor = binding.bookAuthor
        private val bookDescription = binding.bookDescription
        private val bookPrice = binding.bookPrice

        @SuppressLint("SetTextI18n")
        fun bindView(bookData: BookData) {

            bookName.text = bookData.title
            bookAuthor.text =bookData.author
            bookDescription.text = bookData.description

            if(bookData.price != "unknown"){
                bookPrice.text = bookData.price+"원"
            }

            Glide.with(context).load(bookData.image)
                    .placeholder(R.drawable.ic_baseline_insert_photo_24)
                    .transform(CenterCrop(), RoundedCorners(20))
                    .into(bookImg)



        }

    }

}