package com.wcsm.androidlearninghub.guide_api

import com.wcsm.androidlearninghub.guide_api.model.Endereco
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoAPI {

    @GET("ws/{cep}/json/")
    suspend fun recuperarEndereco(
        @Path("cep") cep: String
    ) : Response<Endereco>

}