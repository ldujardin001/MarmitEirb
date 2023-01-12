package com.example.myapplication.model

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.annotations.SerializedName

class IngredientMeasure(val ingredient: String, val measure: String) {

    override fun toString(): String {
        return "IngredientMeasure(ingredient='$ingredient', measure='$measure')"
    }
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

    override fun toString(): String {
        return "RecipeResponse(id=$id, name=$name, instructions=$instructions, thumb=$thumb, youtube=$youtube)"
    }

    var ingredients = mutableListOf<IngredientMeasure>()

    fun serializeIngredients(json:String) {

        var jsonObj: JsonObject? = Gson().fromJson(json, JsonObject::class.java)
        if(jsonObj != null) {
            var meal = jsonObj.get("meals").asJsonArray.get(0).asJsonObject
            for (i in 1..20) {
                Log.d("ingredient$i", (meal.get("strIngredient$i").isJsonNull).toString())
                if (! meal.get("strIngredient$i").isJsonNull) {
                    val ing = meal.get("strIngredient$i").asString
                    val mea = meal.get("strMeasure$i").asString
                    if (ing != "") {
                        val ingredient = IngredientMeasure(ing, mea)
                        ingredients.add(ingredient)
                    }
                }
            }
        }

        Log.d("Ingredients ser", ingredients.toString())

    }
}
