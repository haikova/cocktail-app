package olyarisu.github.com.cocktailapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cocktail.view.*
import olyarisu.github.com.cocktailapp.R
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteCocktail
import olyarisu.github.com.cocktailapp.domain.entities.FavouriteList

class FavouriteCocktailAdapter(
    private var listCocktails: FavouriteList,
    private val context: Context,
    private val onItemClick: ((FavouriteCocktail) -> Unit)? = null
) : RecyclerView.Adapter<FavouriteCocktailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteCocktailViewHolder {
        return FavouriteCocktailViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cocktail,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: FavouriteCocktailViewHolder, position: Int) {
        viewHolder.apply {
            listCocktails.favourites?.let{ cocktail ->
                titleCocktail.text = cocktail[position].name
                Glide.with(context)
                    .load(cocktail[position].photoURL)
                    .into(imageCocktail)
                itemView.setOnClickListener { onItemClick?.invoke(cocktail[position]) }}
        }
    }

    override fun getItemCount(): Int = listCocktails.favourites?.size ?: 0
}

class FavouriteCocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageCocktail = itemView.image_cocktail
    val titleCocktail = itemView.title_cocktail
}