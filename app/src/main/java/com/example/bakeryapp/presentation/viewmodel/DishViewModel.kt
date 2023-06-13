package com.example.bakeryapp.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakeryapp.data.model.Dishes
import com.example.bakeryapp.data.model.ResponseDishes
import com.example.bakeryapp.domain.usecases.BakeryInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DishViewModel(
    private val handle: SavedStateHandle,
    private val bakeryInteractor: BakeryInteractor
) : ViewModel() {

    val tagState = mutableStateOf(Tags.ALL_MENU)

    val dishesState = mutableStateOf<ResponseDishes?>(null)

    val titleState = mutableStateOf(handle.get<String>("category"))

    fun addToBasket(dishes: MutableList<Dishes>) {
        handle["basket"] = dishes
    }

    private fun getDishes() {
        viewModelScope.launch {
            bakeryInteractor.getDishes()
                .flowOn(Dispatchers.IO)
                .catch { Log.d("GGG", "${it.message}") }
                .collect {
                    dishesState.value = it
                    Log.d("GGG", "${it.dishes}")
                }
        }
    }

    init {
        getDishes()
    }

    enum class Tags(val title: String) {
        ALL_MENU("Все меню"),
        RICE("С рисом"),
        SALAD("Салаты"),
        FISH("С рыбой")
    }
}