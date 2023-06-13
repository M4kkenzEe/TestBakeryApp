package com.example.bakeryapp.data.repository

import android.util.Log
import com.example.bakeryapp.data.api.BakeryApi
import com.example.bakeryapp.data.model.ResponseDishes
import com.example.bakeryapp.domain.repository.BakeryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BakeryRepositoryImpl(private val api: BakeryApi) : BakeryRepository {
    override suspend fun getDishes(): Flow<ResponseDishes> {
        Log.d("GGG", "IMPL is working")
        return flow {
            emit(api.getDishes())
        }
    }

    override suspend fun getCategories() = flow {
        emit(api.getCategories())
    }
}



