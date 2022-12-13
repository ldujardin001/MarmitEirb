package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Meal
import com.squareup.picasso.Picasso

class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var nameTextView: TextView = itemView.findViewById(R.id.item_meal_name)
    var imageView: ImageView = itemView.findViewById(R.id.item_image)

    fun initialize(item: Meal, action:OnMealItemClickListener){
        itemView.setOnClickListener{
            action.onItemClick(item, adapterPosition)
        }
    }
}

class MealAdapter(val categories: List<Meal>, var clickListener: OnMealItemClickListener): RecyclerView.Adapter<MealViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.nameTextView.text = categories[position].name
        Picasso.get().load(categories[position].thumb).into(holder.imageView)

        holder.initialize(categories[position], clickListener)
    }

    override fun getItemCount(): Int {
        return categories.count()
    }
}

interface OnMealItemClickListener {
    fun onItemClick(item: Meal, position: Int)
}