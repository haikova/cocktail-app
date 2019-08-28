package olyarisu.github.com.cocktailapp.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_category.view.*
import olyarisu.github.com.cocktailapp.ALCOHOLIC
import olyarisu.github.com.cocktailapp.CATEGORY
import olyarisu.github.com.cocktailapp.GLASSES
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.domain.entities.Category
import olyarisu.github.com.cocktailapp.domain.entities.Cocktail

class CategoriesAdapter(
    private val categories: ArrayList<Category>,
    //private var listCategories: List<String>,
    private val context: Context,
    private val onItemClick: ((Category) -> Unit)? = null
) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_category,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, position: Int) {
        viewHolder.apply {
            titleCategory.text = categories[position].name
            itemView.setOnClickListener { onItemClick?.invoke(categories[position]) }
            when (categories[position].idCategoryGroup) {
                ALCOHOLIC -> resCategory.setBackgroundColor(Color.BLACK)
                GLASSES -> resCategory.setBackgroundColor(Color.RED)
                CATEGORY -> resCategory.setBackgroundColor(Color.MAGENTA)
            }
        }
    }

    override fun getItemCount(): Int = categories.size

    fun add(list: List<Category>) {
        categories.addAll(list)
        notifyDataSetChanged()
    }
}

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleCategory = itemView.title_category
    val resCategory = itemView.image_category
}