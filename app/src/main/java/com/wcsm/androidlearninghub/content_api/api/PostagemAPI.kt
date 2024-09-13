package com.wcsm.androidlearninghub.content_api.api

import com.wcsm.androidlearninghub.content_api.model.Postagem
import retrofit2.Response
import retrofit2.http.GET

interface PostagemAPI {

    @GET("posts")
    suspend fun recuperarPostagens(): Response<List<Postagem>>

}