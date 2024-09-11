## Consumo de Serviços Web REST e RESTful (API)

Utilizando a biblioteca Retrofit, que é utilizada para fazer requisições HTTP no Android utilizando 
o padrão Rest, também conhecido como Cliente HTTP.

### Configurando o Retrofit

Vamos utilizar a configuração conforme o site oficial [Retrofit](https://www.square.github.io/retrofit/). 
Acesse o site oficial para aprender a configurar as dependências do Gradle.

### Estrutura Base

Segue estrutura base da configuração do Retrofit:
```kotlin
val retrofit = Retrofit.Builder()
	.baseUrl("api.exemplo.com/")
	.addConverterFactory(conversor)
	.build()
```

### Gradle Implementation

#### Retrofit
```kotlin
// Retrofit
implementation("com.squareup.retrofit2:retrofit:2.9.0")
```

#### Gson Converter
```kotlin
// Gson Converter
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
```

### Criando a Classe Helper

#### Classe Helper Individual
```kotlin
class RetrofitHelper {  
    companion object {  
  
        const val API_KEY = "f1d4bdabc6dcb6cb665675fdf8ffc665"  
        const val BASE_URL = "https://api.themoviedb.org/3/"  
        const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/"  
        const val TAMANHO_MAIOR = "w1280"  
  
  
        val apiViaCEP = Retrofit.Builder()  
            .baseUrl("https://viacep.com.br/")  
            .addConverterFactory( GsonConverterFactory.create() )//json ou XML  
            .build()  
  
        val retrofit = Retrofit.Builder()  
            .baseUrl("https://jsonplaceholder.typicode.com/")  
            .addConverterFactory( GsonConverterFactory.create() )//json ou XML  
            .build()  
  
        val filmeAPI = Retrofit.Builder()  
            .baseUrl( BASE_URL )  
            .addConverterFactory( GsonConverterFactory.create() )//json ou XML  
            .build()  
            .create( FilmeAPI::class.java )  
  
    }  
}
```

#### Classe Helper Genérica (Melhor Forma)
```kotlin
// Objeto do Retrofit de forma Genérica
object RetrofitService {  
  
    fun <T> getApiData(apiClass: Class<T>, baseUrl: String) : T {  
        return Retrofit.Builder()  
            .baseUrl(baseUrl)  
            .addConverterFactory(GsonConverterFactory.create())  
            .client(  
                OkHttpClient.Builder()  
                    .addInterceptor(AuthInterceptor())  
                    .build()  
            )  
            .build()  
            .create(apiClass)  
    }  
}

// Auth Interceptor para injetar a API Key ao Header da Requiisição
class AuthInterceptor : Interceptor {  
  
    //private val xRapidApiKey = EnvironmentVariables.X_RAPID_API_KEY  
    //private val xRapidApiHost = EnvironmentVariables.X_RAPID_API_HOST  

	private val xRapidApiKey = "testeApiKey"
	private val xRapidApiHost = "testeApiHost"

    override fun intercept(chain: Interceptor.Chain): Response {  
        val requestBuilder = chain.request().newBuilder()  
        val request = requestBuilder.addHeader(  
            "X-RapidAPI-Key", xRapidApiKey  
        ).addHeader(  
            "X-RapidAPI-Host", xRapidApiHost  
        ).build()  
  
        return chain.proceed(request)  
    }  
}

// Interface Utilizada neste Exemplo
interface WordsAPI {

    @GET("{word}")  
    suspend fun getWordDefinition(  
        @Path("word") word: String  
    ) : Response<WordResponse>  
}

// Usando a API Genérica
private val wordsAPI by lazy {  
    RetrofitService.getApiData(  
        WordsAPI::class.java,  
        RetrofitService.WORDS_BASE_URL  
    )  
}

// Obter a response da API
private fun getWordData(word: String) {
	binding.wordInputLayout.error = null  
	getWordDataJob = CoroutineScope(Dispatchers.IO).launch {  
	    var response: Response<WordResponse>? = null  
	  
	    try {
	        response = wordsAPI.getWordDefinition(word)  
	    } catch (e: Exception) {  
	        e.printStackTrace()  
	    }  
	  
	    val isValidResponse = response != null && response.isSuccessful  
	    if(isValidResponse) {  
	        val result = response?.body()  
	        if(result != null) {  
	            withContext(Dispatchers.Main) {  
	                populateScreenWithWordData(result)  
	            }  
	        }  
	    } else {  
	        withContext(Dispatchers.Main) {  
	            binding.wordInputLayout.errorIconDrawable = null  
	            binding.wordInputLayout.error = "Palavra não encontrada."  
	            showHideLoading(false)
	            binding.btnSearch.isEnabled = true  
	        }  
	    }  
	}
}
```

### Criação da Interface e Requisição

Aqui um exemplo de vários métodos de uma [Interface de uma API de Filmes](InterfaceRequestExample.md).