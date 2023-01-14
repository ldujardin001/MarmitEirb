package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.*
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

class FavoriteActivity: AppCompatActivity(), OnMealItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mealAdapter: MealAdapter
    private lateinit var circularProgressIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        // if we want a loading indicator
        circularProgressIndicator = findViewById(R.id.progress_circular)

        // inutile ici, car tout est visible par défaut mais pratique si on veut invisibiliser
        circularProgressIndicator.visibility = View.VISIBLE

        displayMeals()
    }

    override fun onResume() {
        super.onResume()
        displayMeals()
    }

    private fun displayMeals() {
        runOnUiThread {
            refreshView(FavoriteMeal.fav_meals)
        }
    }

    private fun refreshView(it1: List<Meal>) {
        circularProgressIndicator.visibility = View.GONE
        mealAdapter = MealAdapter(it1, this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                baseContext,
                layoutManager.orientation
            )
        )
        recyclerView.adapter = mealAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onItemClick(item: Meal, position: Int) {
        Log.d("Ok","On clique sur le bouton")
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra("recipe_id", item.id)
        startActivity(intent)
    }

}