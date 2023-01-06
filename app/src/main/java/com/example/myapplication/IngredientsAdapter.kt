package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.IngredientMeasure
import com.squareup.picasso.Picasso

class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var ingredientTextView: TextView = itemView.findViewById(R.id.item_ingredient_name)
    var measureTextView: TextView = itemView.findViewById(R.id.item_ingredient_measure)
}
class IngredientsAdapter(val ingredients:MutableList<IngredientMeasure>): RecyclerView.Adapter<IngredientsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return IngredientsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.ingredientTextView.text = ingredients[position].ingredient
        holder.measureTextView.text = ingredients[position].measure
    }

    override fun getItemCount(): Int {
        return ingredients.count()
    }
}