package olyarisu.github.com.cocktailapp.domain.entities

import androidx.annotation.Keep
import com.google.firebase.firestore.IgnoreExtraProperties

@Keep
@IgnoreExtraProperties
data class Ingredient(
        val name: String? = null,
        val measure: String? = null
)