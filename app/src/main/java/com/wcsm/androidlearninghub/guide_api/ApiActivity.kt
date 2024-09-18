package com.wcsm.androidlearninghub.guide_api

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityApiBinding
import com.wcsm.androidlearninghub.guide_api.api.EnderecoAPI
import com.wcsm.androidlearninghub.guide_api.api.PostagemAPI
import com.wcsm.androidlearninghub.guide_api.api.RetrofitHelper
import com.wcsm.androidlearninghub.guide_api.api.RetrofitHelper.Companion.apiJsonPlace
import com.wcsm.androidlearninghub.guide_api.api.RetrofitHelper.Companion.apiViaCep
import com.wcsm.androidlearninghub.guide_api.model.Comentario
import com.wcsm.androidlearninghub.guide_api.model.Endereco
import com.wcsm.androidlearninghub.guide_api.model.Foto
import com.wcsm.androidlearninghub.guide_api.model.Postagem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ApiActivity : AppCompatActivity() {

    private val binding by lazy { ActivityApiBinding.inflate(layoutInflater) }
    private val apiViaCep by lazy { RetrofitHelper.apiViaCep }
    private val apiJsonPlace by lazy { RetrofitHelper.apiJsonPlace }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            btnBuscar.setOnClickListener {

                val cepDigitado = editCep.text.toString()

                CoroutineScope(Dispatchers.IO).launch {
                    val retorno = recuperarEndereco(cepDigitado)

                    if (retorno != null) {
                        withContext(Dispatchers.Main) {
                            binding.tvResultadoCep.text = retorno
                        }
                    }
                }
            }

            btnBuscarJsonApi.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val retorno = recuperarPostagens()

                    if (retorno != null) {
                        withContext(Dispatchers.Main) {
                            var resultado = ""
                            retorno.forEach {
                                resultado += "id: ${it.id}\ntitle: ${it.title}\ndescription: ${it.description}\n\n"
                            }
                            binding.tvResultadoPostagens.text = resultado
                        }
                    }

                    recuperarFotoUnica()
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            recuperarPostagemUnica()

            delay(1000L)

            recuperarComentariosParaPostagem()

            salvarPostagem()

            atualizarPostagem()

            removerPostagem()
        }
    }

    private suspend fun recuperarFotoUnica() {
        var retorno: Response<Foto>? = null

        try {
            val postagemAPI = apiJsonPlace.create(PostagemAPI::class.java)
            retorno = postagemAPI.recuperarFoto(5)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val foto = retorno.body()
                val resultado = "[${retorno.code()}] - ${foto?.id} - ${foto?.url}"

                Log.i("info_jsonplace", resultado)

                withContext(Dispatchers.Main) {
                    Picasso.get()
                        //.load(foto?.url)
                        .load(R.drawable.picasso) // Para carregar imagem local, porém o uso do
                        // Picasso é recomendado para carregar imagens de internet
                        .resize(100, 200)
                        //.centerInside()
                        //.centerCrop()
                        .placeholder(R.drawable.carregando)
                        //.error(R.drawable.picasso)
                        .into(binding.ivFotoRecuperada)
                }
            } else {
                Log.i("info_jsonplace", "retorno erro: ${retorno.errorBody()}")
                Log.i("info_jsonplace", "retorno code: ${retorno.code()}")
            }
        }
    }

    private suspend fun removerPostagem() {
        var retorno: Response<Unit>? = null

        try {
            val postagemAPI = RetrofitHelper.apiJsonPlace.create(PostagemAPI::class.java)
            retorno = postagemAPI.removerPostagem(1)
            Log.i("info_jsonplace", "retorno: $retorno")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                Log.i("info_jsonplace", "Sucesso ao remover postagem!")
            } else {
                Log.i("info_jsonplace", "retorno erro: ${retorno.errorBody()}")
                Log.i("info_jsonplace", "retorno code: ${retorno.code()}")
            }
        }
    }

    private suspend fun atualizarPostagem() {
        var retorno: Response<Postagem>? = null

        val postagem = Postagem(
            "Corpo da postagem",
            -1,
            null, //"Título da postagem",
            1090
        )

        try {
            val postagemAPI = RetrofitHelper.apiJsonPlace.create(PostagemAPI::class.java)

            /*retorno = postagemAPI.atualizarPostagemPut(
                1,
                postagemPut
            )*/

            retorno = postagemAPI.atualizarPostagemPatch(
                1,
                Postagem("atualizado patch", -1, null, 1090)
            )

            Log.i("info_jsonplace", "retorno normal: $retorno")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {

                val id = postagem.id
                val titulo = postagem.title
                val idUsuario = postagem.userId
                Log.i("info_jsonplace", "retorno code: ${retorno.code()}")
                Log.i("info_jsonplace", "id: $id - titulo: $titulo - idUsuario: $idUsuario")
            } else {
                Log.i("info_jsonplace", "retorno erro: ${retorno.errorBody()}")
                Log.i("info_jsonplace", "retorno code: ${retorno.code()}")
            }
        }
    }

    private suspend fun salvarPostagem() {
        var retorno: Response<Postagem>? = null
        var retornoForm: Response<Postagem>? = null

        val postagem = Postagem(
            "Corpo da postagem",
            -1,
            "Título da postagem",
            1090
        )

        try {
            val postagemAPI = RetrofitHelper.apiJsonPlace.create(PostagemAPI::class.java)

            retorno = postagemAPI.salvarPostagem(postagem)
            Log.i("info_jsonplace", "retorno normal: $retorno")

            retornoForm = postagemAPI.salvarPostagemFormulario(
                1090,
                -1,
                "Titulo da postagem Formulario",
                "Corpo da postagem"
            )
            Log.i("info_jsonplace", "retorno formUrlEncoded: $retornoForm")

        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {

                val id = postagem.id
                val titulo = postagem.title
                val idUsuario = postagem.userId
                Log.i("info_jsonplace", "retorno code: ${retorno.code()}")
                Log.i("info_jsonplace", "id: $id - titulo: $titulo - idUsuario: $idUsuario")
            } else {
                Log.i("info_jsonplace", "retorno erro: ${retorno.errorBody()}")
                Log.i("info_jsonplace", "retorno code: ${retorno.code()}")
            }
        }
    }

    private suspend fun recuperarComentariosParaPostagem() {
        var retorno: Response<List<Comentario>>? = null

        try {
            val postagemAPI = RetrofitHelper.apiJsonPlace.create(PostagemAPI::class.java)
            //retorno = postagemAPI.recuperarComentariosParaPostagem(1) // Path
            retorno = postagemAPI.recuperarComentariosParaPostagemQuery(1) // Query
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val listaComentarios = retorno.body()
                var resultado = ""
                listaComentarios?.forEach { comentario ->
                    val idComentario = comentario.id
                    val email = comentario.email
                    val comentarioResultado = "$idComentario - $email\n"
                    resultado += comentarioResultado
                }
                Log.i("info_jsonplace", "$resultado")
            }
        }
    }

    private suspend fun recuperarPostagemUnica() {
        var retorno: Response<Postagem>? = null

        try {
            val postagemAPI = apiJsonPlace.create(PostagemAPI::class.java)
            retorno = postagemAPI.recuperarPostagemUnica(1)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if (retorno != null) {
            if (retorno.isSuccessful) {
                val postagem = retorno.body()
                val resultado = "${postagem?.id} - ${postagem?.title}"
                Log.i("info_jsonplace", resultado)
            }
        }
    }
}

private suspend fun recuperarPostagens(): List<Postagem>? {
    var retorno: Response<List<Postagem>>? = null

    try {
        val postagemAPI = apiJsonPlace.create(PostagemAPI::class.java)
        retorno = postagemAPI.recuperarPostagens()
    } catch (e: Exception) {
        e.printStackTrace()
        Log.i("info_jsonplace", "erro ao recuperar")
    }

    if (retorno != null) {
        if (retorno.isSuccessful) {
            val listaPostagens = retorno.body()
            listaPostagens?.forEach { postagem ->
                val id = postagem.id
                val title = postagem.title

                Log.i("info_jsonplace", "$id - $title")
            }
            return listaPostagens
        }
    }

    return null
}

private suspend fun recuperarEndereco(cep: String): String? {
    var retorno: Response<Endereco>? = null
    //val cepDigitadoUsuario = "01001000"

    try {
        val enderecoAPI = apiViaCep.create(EnderecoAPI::class.java)
        retorno = enderecoAPI.recuperarEndereco(cep)
    } catch (e: Exception) {
        e.printStackTrace()
        Log.i("info_endereco", "erro ao recuperar")
    }

    if (retorno != null) {
        if (retorno.isSuccessful) {
            val endereco = retorno.body()
            Log.i("info_endereco", "endereco: $endereco")
            return endereco.toString()
        }
    }

    return null
}
