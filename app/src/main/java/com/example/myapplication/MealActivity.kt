package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Category
import com.example.myapplication.model.MealsResponse
import com.example.myapplication.model.Meal
import com.example.myapplication.model.RecipeResponse
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

class MealActivity  : AppCompatActivity(), OnMealItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryName: TextView
    private lateinit var descriptionView: TextView
    private lateinit var mealAdapter: MealAdapter
    private lateinit var circularProgressIndicator: CircularProgressIndicator
    private var mealsResponse: MealsResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        recyclerView = findViewById(R.id.recycler_view)
        descriptionView = findViewById(R.id.description)
        categoryName = findViewById(R.id.category_name)
        // if we want a loading indicator
        circularProgressIndicator = findViewById(R.id.progress_circular)

        // inutile ici, car tout est visible par défaut mais pratique si on veut invisibiliser
        circularProgressIndicator.visibility = View.VISIBLE


        val url = URL("https://www.themealdb.com/api/json/v1/1/filter.php?c=" + intent.getStringExtra("category_name"))
        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", e.localizedMessage)
                /*runOnUiThread {
                    circularProgressIndicator.visibility = View.GONE
                }*/
            }
            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    mealsResponse = parseMealsResponse(it)
                    displayMeals()
                    Log.d("OKHTTP", "Got " + mealsResponse?.meals?.count())
                }
            }
        })
    }

    private fun displayMeals() {
        mealsResponse?.meals?.let { it ->
            runOnUiThread {
                refreshView(it)
            }
        }
    }

    private fun parseMealsResponse(json: String): MealsResponse? {
        val gson = Gson()
        return gson.fromJson(json, MealsResponse::class.java)
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
        categoryName.text = intent.getStringExtra("category_name")
        descriptionView.text = intent.getStringExtra("category_description")
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