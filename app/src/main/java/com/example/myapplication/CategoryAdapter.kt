package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Category
import com.example.myapplication.R

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var nameTextView: TextView = itemView.findViewById(R.id.item_category_name)
    var imageView: ImageView = itemView.findViewById(R.id.item_image)

    //fun initialize(item: Category, action:OnCategoryItemClickListener){}
}

class CategoryAdapter(val categories: List<Category>): RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.nameTextView.text = categories[position].name

        //holder.imageView.setImageURI(categories[position].thumb)
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    // fun loadBitmapImage(url: String): Bitmap {}
}



interface OnCategoryItemClickListener {
    fun onItemClick(item: Category, position: Int) {

    }
}