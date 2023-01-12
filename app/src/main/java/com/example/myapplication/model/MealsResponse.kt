package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

class MealsResponse {
    var meals: List<Meal>? = null
}
class Meal {
    @SerializedName("idMeal")
    var id: String? = null
    @SerializedName("strMeal")
    var name: String? = null
    @SerializedName("strMealThumb")
    var thumb: String? = null

    constructor(id: String?, name: String?, thumb: String?) {
        this.id = id
        this.name = name
        this.thumb = thumb
    }
}
