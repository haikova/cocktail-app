package olyarisu.github.com.cocktailapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_category.view.*
import olyarisu.github.com.cocktailapp.R

class CategoriesAdapter (
    private var listCategories: MutableList<String>,
    private val context: Context
) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, position: Int) {
        viewHolder.apply {
            titleCategory.text = listCategories[position]
        }
    }

    override fun getItemCount(): Int = listCategories.size
}

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleCategory = itemView.title_category
    val resCategory = itemView.image_category
}