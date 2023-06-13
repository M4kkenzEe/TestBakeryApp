package com.example.bakeryapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.bakeryapp.data.model.Dishes
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class BasketViewModel(
    private val handle: SavedStateHandle,
) : ViewModel() {
    private val _dishListState = mutableStateListOf<Dishes>()
    val dishListState: SnapshotStateList<Dishes> = _dishListState

    init {
        rewriteData()
    }

    private fun rewriteData() {
        val buf1 = handle.get<MutableList<Dishes>>("basket") ?: emptyList()
        val buf2 = handle.get<MutableList<Dishes>>("basketData") ?: emptyList()
        handle["basketData"] = (buf1 + buf2) as MutableList
        _dishListState.addAll(handle.get<MutableList<Dishes>>("basketData") ?: emptyList())
        handle["basket"] = null
    }

    fun addDish(dish: Dishes) {
        _dishListState.add(dish)
        updateHandle()
    }

    fun removeDish(dish: Dishes) {
        _dishListState.remove(dish)
        updateHandle()
    }

    private fun updateHandle() {
        handle["basketData"] = _dishListState.toMutableList()
    }

    val totalPrice: String
        get() = formatPrice(_dishListState.sumOf { it.price })

    private fun formatPrice(price: Int): String {
        val symbols = DecimalFormatSymbols(Locale.getDefault())
        symbols.groupingSeparator = ' '
        val decimalFormat = DecimalFormat("#,###.##", symbols)
        return decimalFormat.format(price)
    }
}

//fun fff() {
//    val currentData = handle.get<MutableList<Dishes>>("basket") ?: emptyList()
//    val newData = currentData + _dishListState
//    handle["basketData"] = newData
//}

