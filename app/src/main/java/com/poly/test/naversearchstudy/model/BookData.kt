package com.poly.test.naversearchstudy.model

import java.io.Serializable

data class BookData(
        var author: String? = null,
        var description: String? = null,
        var title: String? = null,
        var image: String? = null,
        var price: String? = null
) : Serializable
