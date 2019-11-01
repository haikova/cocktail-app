package olyarisu.github.com.cocktailapp.data.mapper

interface Mapper<T1, T2> {
    fun map(value: T1): T2
}