package com.wcsm.androidlearninghub.guide_api.api

import com.wcsm.androidlearninghub.guide_api.model.Comentario
import com.wcsm.androidlearninghub.guide_api.model.Postagem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostagemAPI {

    @GET("posts")
    suspend fun recuperarPostagens(): Response<List<Postagem>>

    @GET("posts/{id}")
    suspend fun recuperarPostagemUnica(
        @Path("id") id: Int
    ): Response<Postagem>

    @GET("posts/{id}/comments")
    suspend fun recuperarComentariosParaPostagem(
        @Path("id") id: Int
    ): Response<List<Comentario>>

}