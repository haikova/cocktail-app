package olyarisu.github.com.cocktailapp.domain.entities

import androidx.annotation.Keep
import com.google.firebase.firestore.IgnoreExtraProperties

@Keep
@IgnoreExtraProperties
data class Cocktail(
        val id: Int? = null,
        val name: String? = null,
        val instruction: String? = null,
        val ingredients: List<Ingredient>? = null,
        val category: String? = null,
        val alcoholic: String? = null,
        val glass: String? = null,
        val photoURL: String? = null
)