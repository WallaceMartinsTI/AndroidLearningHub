package com.wcsm.androidlearninghub.guide_koin.core.data

import android.content.SharedPreferences
import com.wcsm.androidlearninghub.guide_koin.feature.domain.Repository

class RepositoryImpl2(
    private val preferences: SharedPreferences
): Repository {
    override suspend fun getData(): String {
        preferences
        return "data 2"
    }
}