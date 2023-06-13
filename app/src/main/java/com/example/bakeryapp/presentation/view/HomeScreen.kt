package com.example.bakeryapp.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.*
import com.example.bakeryapp.R
import com.example.bakeryapp.presentation.view.utils.TopBar
import com.example.bakeryapp.presentation.viewmodel.CategoryViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    nextScreen: () -> Unit,
    viewModel: CategoryViewModel = koinViewModel()
) {

    Scaffold(
        topBar = { TopBar() },
        modifier = Modifier.fillMaxSize()
    ) {
        if (viewModel.categoriesState.value == null) {
            CircularProgressIndicator()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 18.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                viewModel.categoriesState.value?.categories?.forEach {
                    item {
                        CategoryCard(title = it.name, imgUrl = it.image_url) {
                            viewModel.saveCategoryHandle(it.name)
                            nextScreen()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CategoryCard(title: String = "", imgUrl: String = "", click: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(148.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { click() },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imgUrl,
            contentDescription = null,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, start = 16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = title,
                fontWeight = FontWeight(500),
                fontSize = 20.sp,
                modifier = Modifier
                    .width(190.dp)
                    .padding(start = 16.dp)
            )
        }
    }
}


@Preview
@Composable
fun HomeScreenPrev() {

    TopBar()

}