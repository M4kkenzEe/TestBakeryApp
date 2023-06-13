package com.example.bakeryapp.domain.usecases

import com.example.bakeryapp.data.model.ResponseCategories
import com.example.bakeryapp.domain.repository.BakeryRepository
import kotlinx.coroutines.flow.Flow

class BakeryInteractor(private var repository: BakeryRepository) {

    suspend fun getDishes() = repository.getDishes()

    suspend fun getCategories(): Flow<ResponseCategories> {
        return repository.getCategories()
    }
}