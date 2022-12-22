package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

class IngredientMeasure {
    var ingredient:String? = null
    var measure:String? = null
}

class RecipeResponse {
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
    @SerializedName("strIngredient1")
    var ingredient1:String? = null
    @SerializedName("strIngredient2")
    var ingredient2:String? = null
    @SerializedName("strMeasure1")
    var measure1:String? = null
    @SerializedName("strMeasure2")
    var measure2:String? = null

    //var ingredients: List<IngredientMeasure>? = null

    fun serializeIngredients(json:String) {
        for(i in 0..19){
        }
    }
}
