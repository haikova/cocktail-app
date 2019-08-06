package olyarisu.github.com.cocktailapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_ingredient.view.*
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.domain.entities.Ingredient

class IngredientsAdapter (
    private var listIngredients: MutableList<Ingredient>,
    private var listMeasures: MutableList<String>,
    private val context: Context
) : RecyclerView.Adapter<IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false))
    }

    override fun onBindViewHolder(viewHolder: IngredientViewHolder, position: Int) {
        viewHolder.apply {
            titleIngredient.text = listIngredients[position].name
            measureIngredient.text = listMeasures[position]
        }
    }

    override fun getItemCount(): Int = listIngredients.size
}

class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleIngredient = itemView.ingredient_name
    val measureIngredient = itemView.ingredient_measure
    val photoIngredient = itemView.ingredient_photo
}