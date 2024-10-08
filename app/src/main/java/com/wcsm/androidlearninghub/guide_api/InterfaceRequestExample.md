## Criação da Interface e Requisição

```kotlin
import com.jamiltondamasceno.aulathreadscoroutines.model.Comentario
import com.jamiltondamasceno.aulathreadscoroutines.model.Foto
import com.jamiltondamasceno.aulathreadscoroutines.model.Postagem
import retrofit2.Response
import retrofit2.http.*

interface PostagemAPI {
    //https://jsonplaceholder.typicode.com/ + posts

    @GET("posts")
    suspend fun recuperarPostagens(): Response<List<Postagem>>
    
    @GET("posts/{id}")
    suspend fun recuperarPostagemUnica(
        @Path("id") id: Int
    ): Response<Postagem>
    
    @GET("posts/{id}/comments")//Path
    suspend fun recuperarComentariosParaPostagem(
        @Path("id") id: Int
    ): Response<List<Comentario>>
    
    @GET("comments")//Query comments ?postId=1&idComentario=2&...
    suspend fun recuperarComentariosParaPostagemQuery(
        @Query("postId") id: Int
    ): Response<List<Comentario>>
    
    @GET("pesquisa/{marca}/{modelo}")//Path
    suspend fun pesquisaPath(
        @Path("marca") marca: String,
        @Path("modelo") modelo: String,
    ): Response<List<Comentario>>
    
    @GET("pesquisa")//Query pesquisa?marca=&modelo=civic
    suspend fun pesquisaQuery(
        @Query("marca") marca: String,
        @Query("modelo") modelo: String,
    ): Response<List<Comentario>>
    
    @POST("posts")
    suspend fun salvarPostagem(
        @Body postagem: Postagem
    ): Response<Postagem>
    
    @FormUrlEncoded
    @POST("posts")
    suspend fun salvarPostagemFormulario(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String,
    ): Response<Postagem>
    
    @PUT("posts/{id}")//Atualização completa
    suspend fun atualizarPostagemPut(
        @Path("id") id: Int,
        @Body postagem: Postagem
    ): Response<Postagem>
    
    @PATCH("posts/{id}")//Atualização parcial
    suspend fun atualizarPostagePatch(
        @Path("id") id: Int,
        @Body postagem: Postagem
    ): Response<Postagem>
    
    @DELETE("posts/{id}")//Remover postagem
    suspend fun removerPostagem(
        @Path("id") id: Int
    ): Response<Unit>
    
    @GET("photos/{id}")
    suspend fun recuperarFoto(
        @Path("id") id: Int
    ): Response<Foto>
}
```