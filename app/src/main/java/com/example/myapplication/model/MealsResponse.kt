package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

class MealsResponse {
    var meals: List<Meal>? = null
}

class IngredientMeasure {
    var ingredient: String? = null
    var measure: String? = null
}

class Meal {
    @SerializedName("idMeal")
    var id: String? = null
    @SerializedName("strMeal")
    var name: String? = null
    @SerializedName("strInstructions")
    var instructions: String? = null
    @SerializedName("strMealThumb")
    var thumb: String? = null
    @SerializedName("strYoutube")
    var youtube: String? = null

    var ingredients: List<IngredientMeasure>? = null
}
