package com.poly.test.naversearchstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer

abstract class BaseActivity<T: ViewDataBinding>(private val layoutId : Int) : AppCompatActivity() {

    lateinit var mBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.inflate(layoutInflater,layoutId,null,false)

        setContentView(mBinding.root)
        mBinding.lifecycleOwner = this

    }


}