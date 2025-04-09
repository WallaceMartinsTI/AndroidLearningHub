package com.wcsm.androidlearninghub.guide_koin.feature.di

import com.wcsm.androidlearninghub.guide_koin.feature.data.RepositoryImpl
import com.wcsm.androidlearninghub.guide_koin.feature.data.api.SomeApi
import com.wcsm.androidlearninghub.guide_koin.feature.domain.Repository
import com.wcsm.androidlearninghub.guide_koin.feature.presentation.FeatureViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val featureModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(SomeApi.BASE_URL)
            .build()
            .create(SomeApi::class.java)
    }

    single<Repository>(qualifier = named("RepositoryImpl")) { RepositoryImpl(get()) }
    //singleOf(::RepositoryImpl).bind<Repository>()

    viewModel {
        FeatureViewModel(
            get(named("RepositoryImpl"))
        )
    }
}