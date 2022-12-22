package com.example.myapplication


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Meal
import com.example.myapplication.model.MealsResponse
import com.example.myapplication.model.RecipeResponse
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

//data class myClass(val idMeal:String, val strName:String, val strInstruction:String, val strMealThumb:String, val strYoutube:String, val strIngredients:List<String>)

class RecipeActivity : AppCompatActivity(), OnMealItemClickListener {

    //private lateinit var recyclerView: RecyclerView
    //private lateinit var IngredientsAdapter: IngredientsAdapter
    //    private lateinit var circularProgressIndicator: CircularProgressIndicator
    private var recipeResponse: RecipeResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recyclerView = findViewById(R.id.recycler_view)

        // if we want a loading indicator
//        circularProgressIndicator = findViewById(R.id.progress_circular)

        // inutile ici, car tout est visible par défaut mais pratique si on veut invisibiliser
        //circularProgressIndicator.visibility = View.VISIBLE


        val url = URL("https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + intent.getStringExtra("recipe_id"))
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
                    recipeResponse = parseRecipeResponse(it)
                    displayRecipe()
                    Log.d("OKHTTP", "Got recipe")
                }
            }
        })
    }

    private fun displayRecipe() {
        recipeResponse?.let { it ->
            runOnUiThread {
                refreshView(it)
            }
        }
    }

    private fun parseRecipeResponse(json: String): RecipeResponse? {
        val gson = Gson()
        val recipeResponse: RecipeResponse? = gson.fromJson(json, RecipeResponse::class.java)
        //recipeResponse.serializeIngredients(json)
        return recipeResponse
    }

    private fun refreshView(it1: RecipeResponse) {
        //circularProgressIndicator.visibility = View.GONE
        IngredientsAdapter = IngredientsAdapter(it1, this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = IngredientsAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onItemClick(item: Meal, position: Int){
        Log.d("Ok","On clique sur le bouton")

    }

}