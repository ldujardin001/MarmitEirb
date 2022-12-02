package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

class CategoriesResponse {
    var categories: List<Category>? = null
}

class Category {
    @SerializedName("idCategory")
    var id: String? = null
    @SerializedName("strCategory")
    var name: String? = null
    @SerializedName("strCategoryThumb")
    var thumb: String? = null
    @SerializedName("strCategoryDescription")
    var categoryDescription: String? = null
}