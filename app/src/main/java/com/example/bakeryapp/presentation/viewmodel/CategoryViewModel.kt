package com.example.bakeryapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakeryapp.data.model.ResponseCategories
import com.example.bakeryapp.data.model.ResponseDishes
import com.example.bakeryapp.domain.usecases.BakeryInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val bakeryInteractor: BakeryInteractor,
    private val handle: SavedStateHandle,
) : ViewModel() {

    val categoriesState = mutableStateOf<ResponseCategories?>(null)

    private fun getCategories() {
        viewModelScope.launch {
            bakeryInteractor.getCategories()
                .catch { Log.d("GGG", "${it.message}") }
                .collect {
                    saveCategories(it)
//                    categoriesState.value = it
                    Log.d("GGG", "${it.categories}")
                }
        }
    }

    private fun saveCategories(category: ResponseCategories) {
        categoriesState.value = category
    }

    fun saveCategoryHandle(categoryName: String) {
        handle["category"] = categoryName

    }

    init {
        getCategories()
    }
}