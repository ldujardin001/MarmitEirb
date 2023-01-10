package com.example.myapplication


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.IngredientMeasure
import com.example.myapplication.model.Meal
import com.example.myapplication.model.MealsResponse
import com.example.myapplication.model.RecipeResponse
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException
import java.net.URL

//data class myClass(val idMeal:String, val strName:String, val strInstruction:String, val strMealThumb:String, val strYoutube:String, val strIngredients:List<String>)

class RecipeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var view: View
    private lateinit var ingredientsAdapter: IngredientsAdapter
    private lateinit var recipeName: TextView
    private lateinit var instructions: TextView
    private lateinit var youtube: TextView
    private lateinit var image: ImageView
    //    private lateinit var circularProgressIndicator: CircularProgressIndicator
    private var recipeResponse: RecipeResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        recyclerView = findViewById(R.id.recycler_view)

        recipeName = findViewById(R.id.recipe_name)
        instructions = findViewById(R.id.instructions_content)
        youtube = findViewById(R.id.youtube)
        image = findViewById(R.id.recipe_image)


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
        Log.d("json", json)
        val gson = Gson()
        val parser = JsonParser()
        val jsonElement = parser.parse(json)
        var jsonObj = jsonElement.asJsonObject
        var meal = jsonObj.get("meals").asJsonArray.get(0).asJsonObject
        val recipeResponse: RecipeResponse? = gson.fromJson(meal, RecipeResponse::class.java)
        recipeResponse?.serializeIngredients(json)
        Log.d("debug", recipeResponse.toString())
        return recipeResponse
    }

    private fun refreshView(it1: RecipeResponse) {
        //circularProgressIndicator.visibility = View.GONE
        ingredientsAdapter = IngredientsAdapter(it1.ingredients)
        //val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = ingredientsAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recipeName.text = it1.name
        instructions.text = it1.instructions
        youtube.text = it1.youtube
        Picasso.get().load(it1.thumb).into(image)
    }

}