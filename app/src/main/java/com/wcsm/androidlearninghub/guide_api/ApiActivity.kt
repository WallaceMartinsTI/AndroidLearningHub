package com.wcsm.androidlearninghub.guide_api

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityApiBinding
import com.wcsm.androidlearninghub.guide_api.api.EnderecoAPI
import com.wcsm.androidlearninghub.guide_api.api.PostagemAPI
import com.wcsm.androidlearninghub.guide_api.api.RetrofitHelper
import com.wcsm.androidlearninghub.guide_api.api.RetrofitHelper.Companion.apiJsonPlace
import com.wcsm.androidlearninghub.guide_api.api.RetrofitHelper.Companion.apiViaCep
import com.wcsm.androidlearninghub.guide_api.model.Comentario
import com.wcsm.androidlearninghub.guide_api.model.Endereco
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
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            recuperarPostagemUnica()

            delay(1000L)

            recuperarComentariosParaPostagem()
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
