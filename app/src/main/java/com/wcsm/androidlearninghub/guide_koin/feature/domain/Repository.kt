package com.wcsm.androidlearninghub.guide_koin.feature.domain

interface Repository {
    suspend fun getData(): String
}