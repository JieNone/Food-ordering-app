package com.example.testfood.model

import android.os.Parcelable
import com.example.testfood.R
import kotlinx.parcelize.Parcelize


@Parcelize
data class DrinksData(

    val title :String,
    val desc :String,
    val price: Int,
    val imgUrl:Int,
):Parcelable

val drinksList = listOf(
    DrinksData(
        "Спрайт",
        "Прохладительный газированный напиток Спрайт.",
        49,
        R.drawable.news_banner
    ),
    DrinksData(
        "Кока-Кола ",
        "Прохладительный газированный напиток «Кока-Кола».",
        49,
        R.drawable.news_banner
    ),
    DrinksData(
        "Кока-Кола Зеро",
        "Прохладительный газированный напиток «Кока-Кола» Зеро не содержит калорий",
        49,
        R.drawable.news_banner
    ),
    DrinksData(
        "Фанта",
        "Прохладительный газированный напиток Фанта.",
        49,
        R.drawable.news_banner
    ),
    DrinksData(
        "Липтон Айс Ти Лимон",
        "Прохладительный газированный напиток Фанта.",
        49,
        R.drawable.news_banner
    ),
)