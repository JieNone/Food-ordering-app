package com.example.testfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.testfood.model.*
import com.example.testfood.ui.theme.TestFoodTheme
import com.example.testfood.view.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestFoodTheme {
                MainScreen()
            }
        }
    }
}


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Navigation(navController = navController)
    }
}
@Composable
fun ComboViewPagerSlider(navController: NavHostController) {
    Column {
//        TopBar(navController)
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(bottom = 40.dp)
                .fillMaxHeight()

        ) {
            TopBar(navController)
            News(navController)
            ComboCategoriesToolbar(navController)

        }
    }


}

@Composable
fun NestedComponent(){
    val basicState = remember { mutableStateOf(0f) }
    val minBound = -100f
    val maxBound = 100f
// lambda to update state and return amount consumed
    val onNewDelta: (Float) -> Float = { delta ->
        val oldState = basicState.value
        val newState = (basicState.value + delta).coerceIn(minBound, maxBound)
        basicState.value = newState
        newState - oldState
    }
// create a dispatcher to dispatch nested scroll events (participate like a nested scroll child)
    val nestedScrollDispatcher = remember { NestedScrollDispatcher() }

// create nested scroll connection to react to nested scroll events (participate like a parent)
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                // we have no fling, so we're interested in the regular post scroll cycle
                // let's try to consume what's left if we need and return the amount consumed
                val vertical = available.y
                val weConsumed = onNewDelta(vertical)
                return Offset(x = 0f, y = weConsumed)
            }
        }
    }
    Box(
        Modifier
            .size(100.dp)
            .background(Color.LightGray)
            // attach ourselves to nested scroll system
            .nestedScroll(connection = nestedScrollConnection, dispatcher = nestedScrollDispatcher)
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    // here's regular drag. Let's be good citizens and ask parents first if they
                    // want to pre consume (it's a nested scroll contract)
                    val parentsConsumed = nestedScrollDispatcher.dispatchPreScroll(
                        available = Offset(x = 0f, y = delta),
                        source = NestedScrollSource.Drag
                    )
                    // adjust what's available to us since might have consumed smth
                    val adjustedAvailable = delta - parentsConsumed.y
                    // we consume
                    val weConsumed = onNewDelta(adjustedAvailable)
                    // dispatch as a post scroll what's left after pre-scroll and our consumption
                    val totalConsumed = Offset(x = 0f, y = weConsumed) + parentsConsumed
                    val left = adjustedAvailable - weConsumed
                    nestedScrollDispatcher.dispatchPostScroll(
                        consumed = totalConsumed,
                        available = Offset(x = 0f, y = left),
                        source = NestedScrollSource.Drag
                    )
                    // we won't dispatch pre/post fling events as we have no flinging here, but the
                    // idea is very similar:
                    // 1. dispatch pre fling, asking parents to pre consume
                    // 2. fling (while dispatching scroll events like above for any fling tick)
                    // 3. dispatch post fling, allowing parent to react to velocity left
                }
            )
    ) {
        Text(
            "State: ${basicState.value.roundToInt()}",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
@Composable
fun ComboCategoriesToolbar(navController: NavHostController){

    val state = rememberCollapsingToolbarScaffoldState()
//    Column(
//        Modifier
//            .fillMaxSize()
//            .nestedScroll(nestedScrollConnection)
//    ){
    CollapsingToolbarScaffold(
        modifier = Modifier
            .background(Color.White)

            .fillMaxSize(), state = state, scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            Categories(navController)
        },
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize(),)
            {
            items(pizzaList.size){item ->
                Card(
                    Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .clickable(onClick = {
                        })) {
                    ComboItem(navController, comboList[item])
                }
            }
        }
//    }
    }
}

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController, startDestination = NavigationItem.Menu.route) {
        composable(NavigationItem.Menu.route) {
            PizzaViewPagerSlider(navController)
        }
        composable(NavigationItem.Profile.route) {
            ProfileView(navController = navController)
        }
        composable(NavigationItem.Basket.route) {
            BasketView()
        }
        composable(NavigationItem.PizzaCategory.route) {
            PizzaViewPagerSlider(navController)
        }
        composable(NavigationItem.ComboCategory.route) {
            ComboViewPagerSlider(navController)
        }
        composable(NavigationItem.DessertsCategory.route) {
            DessertsViewPagerSlider(navController = navController)
        }
        composable(NavigationItem.DrinksCategory.route) {
            DrinksViewPagerSlider(navController = navController)
        }
        composable(NavigationItem.QrPage.route) {
            QrView()
        }
        composable(route = NavigationItem.DetailDrink.route){

                val resDrinks =
                    navController.previousBackStackEntry?.savedStateHandle?.get<DrinksData>("drinks")
            DrinksPage(item = resDrinks)
        }
        composable(route = NavigationItem.DetailPizza.route){

                val resPizza =
                    navController.previousBackStackEntry?.savedStateHandle?.get<PizzaData>("pizza")
            PizzaPage(item = resPizza)
        }
        composable(route = NavigationItem.DetailCombo.route){

                val resCombo =
                    navController.previousBackStackEntry?.savedStateHandle?.get<ComboData>("combo")
            ComboPage(item = resCombo)
        }
        composable(route = NavigationItem.DetailDesserts.route){
                val resDessert =
                    navController.previousBackStackEntry?.savedStateHandle?.get<DessertsData>("dessert")
            DessertPage(item = resDessert)
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Menu,
        NavigationItem.Profile,
        NavigationItem.Basket
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.DarkGray.copy(0.6f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun PizzaViewPagerSlider(navController: NavHostController) {
    Column {
        TopBar(navController)
        News(navController)
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(bottom = 40.dp)
                .fillMaxHeight()
        ) {
                PizzaCategoriesToolbar(navController)
        }
    }


}
@Composable
fun PizzaCategoriesToolbar(navController: NavHostController){
    val state = rememberCollapsingToolbarScaffoldState()
    CollapsingToolbarScaffold(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight(),
        state = state, scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            Categories(navController)
        },
    ) {
            val scrollState = rememberLazyListState()
            LazyColumn(
                state = scrollState,
                modifier = Modifier.fillMaxSize(),
            ){
                items(pizzaList.size){item ->
                    Card(
                        Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth()
                            .clickable(onClick = {
                            })) {
                        PizzaItem(navController, pizzaList[item])
                    }
                }
            }
        }
    }

@Composable
fun DessertsViewPagerSlider(navController: NavHostController) {

    Column {
        TopBar(navController)
        News(navController)
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(bottom = 40.dp)
                .fillMaxHeight()
        ) {

            DessertsCategoriesToolbar(navController)
        }
    }


}
@Composable
fun DessertsCategoriesToolbar(navController: NavHostController){

    val state = rememberCollapsingToolbarScaffoldState()
    CollapsingToolbarScaffold(
        modifier = Modifier.background(Color.White), state = state, scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar = {
            Categories(navController)
        },
    ) {
        val scrollState = rememberLazyListState()
        LazyColumn(modifier = Modifier.fillMaxSize(), state = scrollState){
            items(dessertsList.size){ item ->
                Card(
                    Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .clickable(onClick = {}),
                    elevation = 2.dp,
                ) {
                    DessertItem(navController, dessertsList[item])
                }
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
    }
}
@Composable
fun DrinksViewPagerSlider(navController: NavHostController) {
    Column {
        TopBar(navController)
        News(navController)
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(bottom = 40.dp)
                .fillMaxHeight()

        ) {

            DrinksCategoriesToolbar(navController)
        }
    }


}
@Composable
fun DrinksCategoriesToolbar(navController: NavHostController){
    val state = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier = Modifier.background(Color.White), state = state, scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar = {
            Categories(navController)
        },
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(drinksList.size){ item ->
                Card(
                    Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .clickable(onClick = {
                        })) {
                    DrinkItem(navController, drinksList[item])
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun News(navController: NavHostController) {
    val pagerState = rememberPagerState(
        pageCount = newsList.size,
        initialPage = 0
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(4000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Column(
        Modifier.height(150.dp)
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                .height(100.dp)
        ) { page ->
            Card(modifier = Modifier
                .height(140.dp)
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.55f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale

                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }

                .fillMaxWidth()
                .padding(5.dp, 0.dp, 5.dp, 0.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { navController.navigate(NavigationItem.QrPage.route) }
            ) {
                val newKids = newsList[page]
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .align(Alignment.TopStart)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(newKids.imgUri),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .wrapContentSize()
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .fillMaxWidth()
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(15.dp)
                    ) {
                    }
                }
            }
        }
    }
}


@Composable
fun TopBar(navController: NavHostController) {

    val cities = listOf("Москва", "Санкт-Петербург", "Екатеринбург", "Калининград", "Уфа", "Пермь")
    val isExpanded = remember { mutableStateOf(false) }
    val currentValue = remember { mutableStateOf(cities[0]) }


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp)
            .padding(16.dp),
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                Row(Modifier.clickable {
                    isExpanded.value = !isExpanded.value

                }) {
                    Text(text = currentValue.value)
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)



                    DropdownMenu(expanded = isExpanded.value, onDismissRequest = {
                        isExpanded.value = false
                    }) {

                        cities.forEach {

                            DropdownMenuItem(onClick = {
                                currentValue.value = it
                                isExpanded.value = false
                            }) {
                                Text(text = it)
                            }
                        }
                    }

                }
            }
            Button(
                onClick = { navController.navigate(NavigationItem.QrPage.route) },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.qr_code_scanner),
                    contentDescription = null
                )
            }
        }

    }
}