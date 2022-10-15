package com.example.testfood.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testfood.NavigationItem
import com.example.testfood.model.ComboData
import com.example.testfood.model.DessertsData
import com.example.testfood.model.DrinksData
import com.example.testfood.model.PizzaData


@Composable
fun PizzaItem(navController: NavController, item: PizzaData) {
    Surface(
        Modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(painter = painterResource(id = item.imgUrl), contentDescription = null,
                Modifier
                    .size(120.dp)
                    .padding(start = 16.dp, end = 16.dp))
            Spacer(modifier = Modifier.size(32.dp))
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        Modifier.padding(end = 8.dp)
                    ) {
                        Text(text = item.title, color = Color.Black)
                        Text(text = item.desc, color = Color.Gray, maxLines = 21,)
                    }
                        Button(
                            onClick = {
                                val pizza = PizzaData(title = item.title, desc = item.desc, imgUrl = item.imgUrl, price = item.price)
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    key = "pizza",
                                    value = pizza
                                )
                                navController.navigate(NavigationItem.DetailPizza.route)
                            },
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(end = 8.dp),
                            colors = ButtonDefaults.buttonColors(Color.White),
                            border = BorderStroke(1.dp, Color.Red),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(text = "от ${item.price} р", color = Color.Red)

                        }
                }
        }
    }

}
@Composable
fun ComboItem(navController: NavController, item: ComboData) {
    Surface(Modifier.padding(top = 16.dp, bottom = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(painter = painterResource(id = item.imgUrl), contentDescription = null,
                Modifier
                    .size(120.dp)
                    .padding(start = 16.dp, end = 16.dp))
            Spacer(modifier = Modifier.size(32.dp))
            Column{
            Text(text = item.title, color = Color.Black)
            Text(text = item.desc, color = Color.Gray, maxLines = 21,)
                Button(
                    onClick = {
                        val combo = ComboData(title = item.title, desc = item.desc, imgUrl = item.imgUrl, price = item.price)
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "combo",
                            value = combo
                        )
                        navController.navigate(NavigationItem.DetailCombo.route)
                              },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = BorderStroke(1.dp, Color.Red),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text( text = "от ${item.price} р", color = Color.Red)

                }
            }
        }
    }

}
@Composable
fun DessertItem(navController: NavController, item : DessertsData) {
    Surface(Modifier.padding(top = 16.dp, bottom = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(painter = painterResource(id = item.imgUrl), contentDescription = null,
                Modifier
                    .size(120.dp)
                    .padding(start = 16.dp, end = 16.dp))
            Spacer(modifier = Modifier.size(32.dp))
            Column{
            Text(text = item.title, color = Color.Black)
            Text(text = item.desc, color = Color.Gray, maxLines = 21,)
                Button(
                    onClick = {
                        val dessert = DessertsData(title = item.title, desc = item.desc, imgUrl = item.imgUrl, price = item.price)
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "dessert",
                            value = dessert
                        )
                        navController.navigate(NavigationItem.DetailDesserts.route)
                              },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = BorderStroke(1.dp, Color.Red),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text( text = "от ${item.price} р", color = Color.Red)

                }
            }
        }
    }

}
@Composable
fun DrinkItem(
    navController: NavController,
    item: DrinksData,
) {

    Surface(Modifier.padding(top = 16.dp, bottom = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(painter = painterResource(id = item.imgUrl), contentDescription = null,
                Modifier
                    .size(120.dp)
                    .padding(start = 16.dp, end = 16.dp))
            Spacer(modifier = Modifier.size(32.dp))
            Column(verticalArrangement = Arrangement.SpaceBetween){

            Text(text = item.title, color = Color.Black)
            Text(text = item.desc, color = Color.Gray, maxLines = 21,)
                Button(
                    onClick = {
                        val drinks = DrinksData(title = item.title, desc = item.desc, imgUrl = item.imgUrl, price = item.price)
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "drinks",
                            value = drinks
                        )
                        navController.navigate(NavigationItem.DetailDrink.route)

                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    border = BorderStroke(1.dp, Color.Red),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text( text = "от ${item.price} р", color = Color.Red)
                }
            }
        }
    }

}


@Composable
fun DrinksPage(
    item: DrinksData?,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ){
    if (item != null) {
        Text(
            text = item.title,
        )
    }
        Spacer(modifier = Modifier.size(40.dp))
        if (item != null) {
            Text(
                text = item.desc,
            )
        }
        Spacer(modifier = Modifier.size(40.dp))

        if (item != null) {
            Text(
                text = item.price.toString(),
            )
        }
        Spacer(modifier = Modifier.size(40.dp))

        if (item != null) {
            Image(painter = painterResource(id = item.imgUrl), contentDescription = null)
        }
    }
}

@Composable
fun DessertPage(
    item: DessertsData?,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ){
        if (item != null) {
            Text(
                text = item.title,
            )
        }
        Spacer(modifier = Modifier.size(40.dp))
        if (item != null) {
            Text(
                text = item.desc,
            )
        }
        Spacer(modifier = Modifier.size(40.dp))

        if (item != null) {
            Text(
                text = item.price.toString(),
            )
        }
        Spacer(modifier = Modifier.size(40.dp))

        if (item != null) {
            Image(painter = painterResource(id = item.imgUrl), contentDescription = null)
        }
    }
}
@Composable
fun ComboPage(
    item: ComboData?,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ){
        if (item != null) {
            Text(
                text = item.title,
            )
        }
        Spacer(modifier = Modifier.size(40.dp))
        if (item != null) {
            Text(
                text = item.desc,
            )
        }
        Spacer(modifier = Modifier.size(40.dp))

        if (item != null) {
            Text(
                text = item.price.toString(),
            )
        }
        Spacer(modifier = Modifier.size(40.dp))

        if (item != null) {
            Image(painter = painterResource(id = item.imgUrl), contentDescription = null)
        }
    }
}
@Composable
fun PizzaPage(
    item: PizzaData?,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ){
    if (item != null) {
        Text(
            text = item.title,
        )
    }
        Spacer(modifier = Modifier.size(40.dp))
        if (item != null) {
            Text(
                text = item.desc,
            )
        }
        Spacer(modifier = Modifier.size(40.dp))

        if (item != null) {
            Text(
                text = item.price.toString(),
            )
        }
        Spacer(modifier = Modifier.size(40.dp))

        if (item != null) {
            Image(painter = painterResource(id = item.imgUrl), contentDescription = null)
        }
    }
}