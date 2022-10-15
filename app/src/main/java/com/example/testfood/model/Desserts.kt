package com.example.testfood.model

import android.os.Parcelable
import com.example.testfood.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class DessertsData(

    val title :String,
    val desc :String,
    val price: Int,
    val imgUrl:Int,
): Parcelable

val dessertsList = listOf(
    DessertsData(
        "Пирожок с вишней",
        "Сочный, горячий пирожок с вишней",
        50,
        R.drawable.news_banner
    ),
    DessertsData(
        "Тирамису ",
        "Нежный классический Тирамису с мягким кофейным бисквитом, муссом со вкусом сыра маскарпоне и ароматным какао.",
        145,
        R.drawable.news_banner
    ),
    DessertsData(
        "Яблочные дольки",
        "Отборные, нарезанные дольками, свежие яблоки",
        55,
        R.drawable.news_banner
    ),
    DessertsData(
        "Макфлурри Спелое Манго",
        "Сочный, горячий пирожок с вишней",
        109,
        R.drawable.news_banner
    ),
    DessertsData(
        "Морковные палочки",
        "Отборная, нарезанная палочками, хрустящая и свежая морковка.",
        55,
        R.drawable.news_banner
    ),
)