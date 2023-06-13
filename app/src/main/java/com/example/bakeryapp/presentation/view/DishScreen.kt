package com.example.bakeryapp.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.bakeryapp.R
import com.example.bakeryapp.data.model.Dishes
import com.example.bakeryapp.presentation.view.utils.TopBarTitle
import com.example.bakeryapp.presentation.viewmodel.DishViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun DishScreen(viewModel: DishViewModel = koinViewModel(), backScreen: () -> Unit) {

    var dialogOpen by remember { mutableStateOf(false) }
    var indexState by remember { mutableStateOf(0) }

    val dishListState by remember { mutableStateOf(mutableListOf<Dishes>()) }

    Scaffold(
        topBar = {
            TopBarTitle(
                title = viewModel.titleState.value ?: "Меню",
                backScreen = { backScreen() })
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            MenuFilter()
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 16.dp, bottom = 70.dp),
            ) {
                viewModel.dishesState.value?.dishes?.forEachIndexed { i, dish ->
                    if (viewModel.tagState.value.title in dish.tegs) {
                        item {
                            DishItem(
                                dishTitle = dish.name,
                                dishIc = dish.image_url,
                                click = {
                                    indexState = i
                                    dialogOpen = true
                                }
                            )
                        }
                    }
                }
            }
            if (dialogOpen) {
                DishDialog(
                    dishTitle = viewModel.dishesState.value!!.dishes[indexState].name,
                    dishIc = viewModel.dishesState.value!!.dishes[indexState].image_url,
                    dishPrice = viewModel.dishesState.value!!.dishes[indexState].price.toString(),
                    dishWeight = viewModel.dishesState.value!!.dishes[indexState].weight.toString(),
                    dishDesc = viewModel.dishesState.value!!.dishes[indexState].description,
                    onDismissReq = { dialogOpen = false },
                    addToBasket = {
                        dishListState.add(viewModel.dishesState.value!!.dishes[indexState])
                        viewModel.addToBasket(dishListState)
                    }
                )
            }

        }
    }
}

@Composable
fun MenuFilter(viewModel: DishViewModel = koinViewModel()) {

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            MenuItem(
                titleTeg = DishViewModel.Tags.ALL_MENU,
                isClicked = viewModel.tagState.value == DishViewModel.Tags.ALL_MENU
            ) {
                viewModel.tagState.value = DishViewModel.Tags.ALL_MENU
            }
        }

        item {
            MenuItem(
                titleTeg = DishViewModel.Tags.RICE,
                isClicked = viewModel.tagState.value == DishViewModel.Tags.RICE
            ) {
                viewModel.tagState.value = DishViewModel.Tags.RICE
            }
        }

        item {
            MenuItem(
                titleTeg = DishViewModel.Tags.SALAD,
                isClicked = viewModel.tagState.value == DishViewModel.Tags.SALAD
            ) {
                viewModel.tagState.value = DishViewModel.Tags.SALAD
            }
        }

        item {
            MenuItem(
                titleTeg = DishViewModel.Tags.FISH,
                isClicked = viewModel.tagState.value == DishViewModel.Tags.FISH
            ) {
                viewModel.tagState.value = DishViewModel.Tags.FISH
            }
        }


    }

}

@Composable
fun MenuItem(titleTeg: DishViewModel.Tags, isClicked: Boolean, clicked: () -> Unit = {}) {
    OutlinedButton(
        onClick = { clicked() },
        colors = ButtonDefaults.buttonColors(
            colorResource(
                id = if (isClicked) R.color.blue_light
                else R.color.white_dark
            )
        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, colorResource(id = R.color.white_dark))
    ) {
        Text(
            text = titleTeg.title,
            fontWeight = FontWeight(400),
            fontSize = 14.sp,
            color = if (isClicked) Color.White else Color.Black
        )
    }
}

@Composable
fun DishItem(
    dishTitle: String = "DishDishDishDishDishDish",
    dishIc: String = "https://i.pinimg.com/originals/1b/30/b8/1b30b865cc25a1a7f7616e50f80b9f58.jpg",
    click: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable { click() },
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = dishIc,
                contentDescription = null,
            )
        }
        Text(
            text = dishTitle,
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier
                .padding(top = 4.dp)
                .width(109.dp),
            textAlign = TextAlign.Start,
        )
    }
}

@Composable
fun DishDialog(
    dishTitle: String = "блюдо",
    dishIc: String = "https://i.pinimg.com/originals/1b/30/b8/1b30b865cc25a1a7f7616e50f80b9f58.jpg",
    dishPrice: String = "777",
    dishWeight: String = "555",
    dishDesc: String = "апжтыапшотцкшоптзкцгцкпзрзцкетмоктикеиортокеитокеищокеищорцкеитцкеощиоцкеиозкертиозтркеитокшеитзкетиткиоткоеитокетиоткеиоткеитошкеитодвтиодти",
    onDismissReq: () -> Unit = {},
    addToBasket: () -> Unit = {},
) {
    Dialog(onDismissRequest = {}) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(343.dp, 470.dp)
                .background(Color.White),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(311.dp, 232.dp)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    AsyncImage(
                        model = dishIc,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Top
                    ) {
                        ReactionButton(icStr = R.drawable.ic_heart)
                        Spacer(modifier = Modifier.width(8.dp))
                        ReactionButton(
                            icStr = R.drawable.ic_x,
                            modifier = Modifier.size(12.dp)
                        ) { onDismissReq() }
                    }
                }
                Text(
                    text = dishTitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(text = "$dishPrice ₽", fontSize = 14.sp, fontWeight = FontWeight(400))
                    Text(
                        text = " · " + dishWeight + "г",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color.Gray
                    )
                }
                Text(
                    text = dishDesc,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier
                        .padding(8.dp)
                        .height(62.dp)
                        .verticalScroll(rememberScrollState()),
                    textAlign = TextAlign.Start,
                )
                Button(
                    onClick = { addToBasket() },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.blue_light),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Добавить в корзину",
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }

            }
        }
    }
}

@Composable
fun ReactionButton(icStr: Int, modifier: Modifier = Modifier, clicked: () -> Unit = {}) {
    Button(
        onClick = { clicked() },
        modifier = Modifier
            .padding(top = 16.dp)
            .size(40.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(0.dp),
    ) {
        Image(
            painter = painterResource(id = icStr),
            contentDescription = null,
            modifier = modifier.size(22.dp)
        )
    }


}

@Preview
@Composable
fun DishPrev() {

//    DishDialog()
}