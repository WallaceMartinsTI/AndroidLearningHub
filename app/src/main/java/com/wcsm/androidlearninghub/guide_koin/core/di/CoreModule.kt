package com.wcsm.androidlearninghub.guide_koin.core.di

import android.content.Context
import com.wcsm.androidlearninghub.guide_koin.core.data.RepositoryImpl2
import com.wcsm.androidlearninghub.guide_koin.core.presentation.KoinViewModel
import com.wcsm.androidlearninghub.guide_koin.feature.domain.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreModule = module {
    single {
        androidContext().getSharedPreferences(
            "pref", Context.MODE_PRIVATE
        )
    }

    single<Repository>(qualifier = named("RepositoryImpl2")) {
        RepositoryImpl2(get())
    }

    viewModel {
        KoinViewModel(
            get(named("RepositoryImpl2"))
        )
    }
}