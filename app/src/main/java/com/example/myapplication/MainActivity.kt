package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.CategoriesResponse
import com.example.myapplication.model.Category
//import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
//    private lateinit var circularProgressIndicator: CircularProgressIndicator
    private var categoriesResponse: CategoriesResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        // if we want a loading indicator
//        circularProgressIndicator = findViewById(R.id.progress_circular)

        // inutile ici, car tout est visible par défaut mais pratique si on veut invisibiliser
        //circularProgressIndicator.visibility = View.VISIBLE


        val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")
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
                    categoriesResponse = parseCategoriesResponse(it)
                    displayPlanets()
                    Log.d("OKHTTP", "Got " + categoriesResponse?.categories?.count())
                }
            }
        })
    }

    private fun displayPlanets() {
        categoriesResponse?.categories?.let { it ->
            runOnUiThread {
                refreshView(it)
            }
        }
    }

    private fun parseCategoriesResponse(json: String): CategoriesResponse? {
        val gson = Gson()
        return gson.fromJson(json, CategoriesResponse::class.java)
    }

    private fun refreshView(it1: List<Category>) {
        //circularProgressIndicator.visibility = View.GONE
        categoryAdapter = CategoryAdapter(it1)
        recyclerView.adapter = categoryAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }
}