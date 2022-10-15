package com.example.testfood.model

import com.example.testfood.R

data class NewsData(

    val title :String,
    val desc :String,
    val imgUri:Int,
)

val newsList = listOf(
    NewsData(
        "Tasty pizza",
        "soooo sweet",
        R.drawable.news_banner

    ),
    NewsData(
        "Tasty pizza",
        "soooo sweet",
        R.drawable.news_banner2    ),
    NewsData(
        "Tasty pizza",
        "soooo sweet",
        R.drawable.news_banner    )


)