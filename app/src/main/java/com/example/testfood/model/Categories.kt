package com.example.testfood.model

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.testfood.NavigationItem

@SuppressLint("SupportAnnotationUsage", "ObsoleteSdkInt")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Categories(navController: NavHostController) {

    val width = 120.dp
    val height = width / 3
    Surface(Modifier.background(Color.White)) {

    LazyRow(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxSize()

    ) {
        items(1) {
            Row {
                Button(
                    onClick = {
                        navController.navigate(NavigationItem.PizzaCategory.route)
                              },
                    modifier = Modifier
                        .size(width, height)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(text = "Пицца", color = Color.Gray, fontWeight = FontWeight.Light)

                }
                Button(
                    onClick = { navController.navigate(NavigationItem.ComboCategory.route) },
                    modifier = Modifier
                        .size(width, height)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(text = "Комбо", color = Color.Gray, fontWeight = FontWeight.Light)

                }
                Button(
                    onClick = { navController.navigate(NavigationItem.DessertsCategory.route) },
                    modifier = Modifier
                        .size(width, height)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(text = "Десерты", color = Color.Gray, fontWeight = FontWeight.Light)
                }
                Button(
                    onClick = { navController.navigate(NavigationItem.DrinksCategory.route) },
                    modifier = Modifier
                        .size(width, height)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(text = "Напитки", color = Color.Gray, fontWeight = FontWeight.Light)

                }
            }

        }
        }
    }
}