package com.example.bakeryapp.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bakeryapp.presentation.view.utils.TopBar
import com.example.bakeryapp.R
import com.example.bakeryapp.presentation.viewmodel.BasketViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BasketScreen(viewModel: BasketViewModel = koinViewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() }) {

        val lazyListState = rememberLazyListState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 70.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp),
                state = lazyListState
            ) {
                viewModel.dishListState
                    .sortedBy { it.name }
                    .groupBy { it }
                    .forEach { (dish, quantity) ->
                        item {
                            BasketItem(
                                dishTitle = dish.name,
                                dishPrice = dish.price.toString(),
                                dishWeight = dish.weight.toString(),
                                dishIc = dish.image_url,
                                quantity = quantity.size.toString(),
                                btnDel = { viewModel.removeDish(dish = dish) },
                                btnAdd = { viewModel.addDish(dish = dish) },
                            )
                        }
                    }
            }

            Button(
                onClick = { },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.blue_light),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Оплатить ${viewModel.totalPrice} ₽",
                    fontWeight = FontWeight(600),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun BasketItem(
    dishTitle: String,
    dishPrice: String,
    dishWeight: String,
    dishIc: String = "",
    quantity: String = "101",
    btnAdd: () -> Unit = {},
    btnDel: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(102.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                AsyncImage(
                    model = dishIc,
                    contentDescription = null,
                    modifier = Modifier.size(62.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = dishTitle, fontSize = 14.sp, fontWeight = FontWeight(400))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 8.dp),
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
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                CalculateItem(
                    quantity = quantity,
                    btnAdd = btnAdd,
                    btnDel = btnDel
                )
            }
        }

    }
}

@Composable
fun CalculateItem(quantity: String, btnAdd: () -> Unit, btnDel: () -> Unit) {
    Row(
        modifier = Modifier
            .size(99.dp, 33.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.gray_light)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "-",
            color = Color.Black,
            fontWeight = FontWeight(500),
            fontSize = 20.sp,
            modifier = Modifier.clickable { btnDel() })

        Text(
            text = quantity,
            color = Color.Black,
            fontWeight = FontWeight(500),
            fontSize = 14.sp,
        )

        Text(
            text = "+",
            color = Color.Black,
            fontWeight = FontWeight(500),
            fontSize = 16.sp,
            modifier = Modifier.clickable { btnAdd() })
    }

}

@Preview
@Composable
fun Prev() {
}