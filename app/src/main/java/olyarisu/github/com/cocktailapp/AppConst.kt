package olyarisu.github.com.cocktailapp

import androidx.annotation.IntDef

const val COCKTAIL_ID = "COCKTAIL_ID"
const val COCKTAIL_APP = "COCKTAIL_APP"

@Target(AnnotationTarget.TYPE)
@IntDef(CATEGORY, GLASSES, ALCOHOLIC)
@Retention(AnnotationRetention.SOURCE)
annotation class CocktailCategory

const val ALCOHOLIC = 0
const val GLASSES = 1
const val CATEGORY = 2
