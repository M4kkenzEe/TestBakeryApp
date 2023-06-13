package com.example.bakeryapp.domain.repository

import com.example.bakeryapp.data.model.ResponseCategories
import com.example.bakeryapp.data.model.ResponseDishes
import kotlinx.coroutines.flow.Flow

interface BakeryRepository {
    suspend fun getDishes(): Flow<ResponseDishes>
    suspend fun getCategories(): Flow<ResponseCategories>
}