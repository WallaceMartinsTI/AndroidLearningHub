## Snackbar

A Snackbar oferece um feedback simples e rápido ao usuário em uma pequena janela que é customizável 
e você pode adicionar uma ação. Exemplo:
```kotlin
import com.google.android.material.snackbar.Snackbar

fun onCreate() {
    binding.btnEnviar.setOnClickListenner { view ->
        Snackbar.make(
            view,
            "Teste",
            Snackbar.LENGTH_LONG
        ).show()
    }
}

fun clickBotao() {
    val snackBar = Snackbar.make(
        view,
        "Mensagem feita com sucesso!",
        Snackbar.LENGHT_SHORT
    )

    snackBar.setACtion("Desfazer") {
        // Ação desejada aqui
        // Como desfazer algo que você fez
    }

    snackBar.show()
}
```

Não esquecer de chamar o .show() no final da Snackbar, se não ela não vai aparecer.

A Snackbar pode receber algumas customizações, como:
```kotlin
private fun exibirSnackBar(view: View) {
	// Criação da Snackbar
	val snackBar = Snackbar.make(  
	    view,  
	    "Alteração feita com sucesso!",  
	    Snackbar.LENGTH_SHORT  
	)  

	// Colocar uma Ação
	snackBar.setAction("Desfazer") {  
	    Toast.makeText(this, "Desfeito", Toast.LENGTH_SHORT).show()  
	}

	// Mudar a cor do Texto
	snackBar.setTextColor(  
	    //resources.getColor(R.color.black)  
	    ContextCompat.getColor(  
	        this,  
	        //android.R.color.holo_orange_dark -> CORES DO ANDROID  
	        R.color.black  
	    )  
	)

	// Mudar a cor do texto de Ação
	snackBar.setActionTextColor(  
	    ContextCompat.getColor(  
	        this,  
	        android.R.color.holo_blue_light  
	    )  
	)

	// Mudar a cor do Fundo
	snackBar.setBackgroundTint(  
	    ContextCompat.getColor(  
	        this,  
	        android.R.color.holo_purple  
	    )  
	)

	// Mostrar a Snackbar
	snackBar.show()
}
```

A Snackbar recebe 3 parâmetros:
- Uma View para exibir a Snackbar (O it de um setOnClickListener é uma View);
- O Texto que será mostrado;
- A Duração da mensagem;

A duração pode ser:
- Snackbar.LENGTH_SHOT;
- Snackbar.LENGTH_LONG;
- Snackbar.LENGTH_INDEFINITE;

A duração LENGTH_INDEFINITE faz com que a Snackbar desapareça somente após ser rejeitada ("dismiss") 
explicitamente ou após ser feito o click na ação.
