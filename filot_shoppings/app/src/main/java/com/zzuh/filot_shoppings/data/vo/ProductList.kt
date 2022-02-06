package com.zzuh.filot_shoppings.data.vo

import com.google.gson.annotations.SerializedName

data class ProductList (
    val list: List<Product>
    )

data class Product(
    val id: Int,
    val imagePath: String,
    val name: String,
    val price: String
    )