package com.example.myapplication


import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.IngredientMeasure
import com.example.myapplication.model.Meal
import com.example.myapplication.model.MealsResponse
import com.example.myapplication.model.RecipeResponse
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

//data class myClass(val idMeal:String, val strName:String, val strInstruction:String, val strMealThumb:String, val strYoutube:String, val strIngredients:List<String>)

class RecipeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var ingredientsAdapter: IngredientsAdapter
    //    private lateinit var circularProgressIndicator: CircularProgressIndicator
    private var recipeResponse: RecipeResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)

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
        Log.d("d", "START PARSE")
        val gson = Gson()
        val recipeResponse: RecipeResponse? = gson.fromJson(json, RecipeResponse::class.java)
        recipeResponse?.serializeIngredients(json)
        return recipeResponse
    }

    private fun refreshView(it1: RecipeResponse) {
        //circularProgressIndicator.visibility = View.GONE
        ingredientsAdapter = IngredientsAdapter(it1.ingredients)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = ingredientsAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        // TODO : Afficher le reste des infos (noms, instructions, yt), mais pas avec adapter car pas liste
        val imageView: ImageView
    }


}