package com.wcsm.androidlearninghub.guide_koin.feature.data

import com.wcsm.androidlearninghub.guide_koin.feature.data.api.SomeApi
import com.wcsm.androidlearninghub.guide_koin.feature.domain.Repository

class RepositoryImpl(
    private val someApi: SomeApi
) : Repository {

    override suspend fun getData(): String {
        someApi
        return "data"
    }
}