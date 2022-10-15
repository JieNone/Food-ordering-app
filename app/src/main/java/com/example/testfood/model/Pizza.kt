package com.example.testfood.model

import android.os.Parcelable
import com.example.testfood.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class PizzaData(

    val title :String,
    val desc :String,
    val price: Int,
    val imgUrl:Int,
): Parcelable

val pizzaList = listOf(
    PizzaData(
        "Маргарита",
        "Тесто с томатной основой, покрытое сыром моцарелла",
        450,
        R.drawable.pizza_item
    ),
    PizzaData(
        "Наполетана ",
        "Сочетание анчоусов с томатной пастой и сыром",
        450,
        R.drawable.pizza_item
    ),
    PizzaData(
        "Веронез",
        "Грибная лепёшка c сыром и ветчиной",
        250,
        R.drawable.pizza_item
    ),
    PizzaData(
        "Каприциоза",
        "Блюдо с вареными яйцами, ветчиной и грибами. В качестве дополнительных ингредиентов добавляют оливки и артишоки",
        450,
        R.drawable.pizza_item
    ),
    PizzaData(
        "Четыре сыра",
        "«Очень сырное» блюдо, в состав которого входит пармезан, рикотта, горгонзола и моцарелла",
        450,
        R.drawable.pizza_item
    ),
)