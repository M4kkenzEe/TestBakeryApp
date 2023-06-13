package com.example.bakeryapp.data.di

import androidx.lifecycle.SavedStateHandle
import com.example.bakeryapp.data.api.RetrofitInstance
import com.example.bakeryapp.data.repository.BakeryRepositoryImpl
import com.example.bakeryapp.domain.repository.BakeryRepository
import com.example.bakeryapp.domain.usecases.BakeryInteractor
import com.example.bakeryapp.presentation.viewmodel.BasketViewModel
import com.example.bakeryapp.presentation.viewmodel.CategoryViewModel
import com.example.bakeryapp.presentation.viewmodel.DishViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single { RetrofitInstance().createApi() }

    single<BakeryRepository> { BakeryRepositoryImpl(api = get()) }

    single { SavedStateHandle() }

    factory { BakeryInteractor(repository = get()) }

    viewModel { CategoryViewModel(bakeryInteractor = get(), handle = get()) }

    viewModel { DishViewModel(bakeryInteractor = get(), handle = get()) }

    viewModel { BasketViewModel(handle = get()) }

}