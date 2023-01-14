package com.example.myapplication

import android.util.Log
import com.example.myapplication.model.Meal

object FavoriteMeal {

    var fav_meals = mutableListOf<Meal>()

    fun add(idMeal: String?, strMeal: String?, strMealThumb: String?){
        if (idMeal!=null && strMeal!=null && strMealThumb!=null){
            fav_meals.add(Meal(idMeal, strMeal, strMealThumb))
            Log.d("Fav meal", "Meal$idMeal,$strMeal added to favorite.")
        }
    }

    fun isFavorite(idMeal: String?): Boolean {
        for (meal in fav_meals){
            if (meal.id.equals(idMeal)){
                return true
            }
        }
        return false
    }

    fun remove(idMeal: String?){
        if (idMeal!=null) {
            for (meal in fav_meals) {
                if (meal.id.equals(idMeal)) {
                    fav_meals.remove(meal)
                    Log.d("Fav meal", "Meal$idMeal, removed from favorite.")
                }
            }
        }
    }

}