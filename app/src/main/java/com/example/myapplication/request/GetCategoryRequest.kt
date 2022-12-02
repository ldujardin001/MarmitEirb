// Quand on fera le refactor et qu'on passera des trucs du main ici

/*
package com.example.myapplication.request

import android.util.Log
import android.view.View
import okhttp3.*
import java.io.IOException
import java.net.URL

class GetCategoryRequest {
    fun getPlanets() {
        val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")
        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", e.localizedMessage)

            }

            override fun onResponse(call: Call, response: Response) {

            }
        }
    }
}*/
