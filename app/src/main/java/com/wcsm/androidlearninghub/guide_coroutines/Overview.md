## Coroutines

### Introdução as Coroutines

Uma coroutine é um padrão de projeto de simultaneidade que você pode usar no Android para 
simplificar o código que é executado de forma assíncrona (também chamadas de Threads Leves).

Coroutines executam dentro de uma Thread.

Sobre os Dispatchers, temos o IO para por exemplo interações com API ou Banco de Dados e, sempre 
que for fazer alterações na UI utilizar a thread Main.

### Como criar uma Coroutine

Criando uma Coroutine:
```kotlin
CoroutineScope(Dispatchers.IO).launch {
	// Código aqui
}
```

Exemplo de uma Coroutine passando atualização de Interface para a UI Thread:
```kotlin
CoroutineScope(Dispatchers.IO).launch {
	repeat(5) { indice -> 
		Log.i("info_coroutine", "Exec: $indice T: ${Thread.currentThread.name}")
		delay(1000)
		withContext(Dispatchers.Main) {
			// Código que afeta a interface
		}
	}
}
```

### Funções Suspensas (suspend)

Em uma função suspensa, é aguardado a finalização de uma linha para seguir para a próxima. 
Exemplo:
```kotlin
suspend fun dadosUsuario() {
	val usuario = recuperarUsuario() // Demora por exemplo 5 segundos
	val postagem = recuperarPostagem(usuario.id)
}
```

Neste caso acima, eu não tenho o usuário.id logo de início, então a execução espera a linha de cima 
ser finalizada para seguir.

Funções Suspensas só podem ser chamadas dentro de uma Coroutine.

### Parar o Controlador de Tempo de Execução de Coroutine

Deve-se criar um Job e associar a coroutine a esse Job, exemplo:
```kotlin
private var job: Job? = null

job = CoroutineScope(Dispatchers.IO).launch {
	// Código aqui
}

// Parando a Coroutine
job?.cancel() // Geralmente utilizado no método onDestroy()
```

Para controlar o tempo de execução de uma coroutine, é utilizado o withTimeout. O código não 
continua a execução ao atingir o tempo especificado(tempo em milissegundos, 1000ms -> 1s), exemplo:
```kotlin
withTimeout(tempo) {
	// Código
}
```

Para fazer alterações na interface deve-se utilizar o withContext:
```kotlin
withContext(Dispatchers.Main).launch {
	// Código que afeta a interface
}
```
