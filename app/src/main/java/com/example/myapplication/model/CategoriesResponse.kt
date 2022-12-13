package com.example.myapplication.model

import android.net.Uri
import com.google.gson.annotations.SerializedName
import java.net.URL

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