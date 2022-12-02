package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Category
import com.example.myapplication.R

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var nameTextView: TextView = itemView.findViewById(R.id.item_category)
}

class CategoryAdapter(val categories: List<Category>): RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.nameTextView.text = categories[position].name
    }

    override fun getItemCount(): Int {
        return categories.count()
    }
}