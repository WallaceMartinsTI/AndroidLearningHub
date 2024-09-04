## Toast

O Toast fornece um feedback simples e rápido ao usuário em uma pequena janela. Exemplo de uso de
um Toast:
```kotlin
import android.widget.Toast
fun clickBotao() {
	Toast.makeText(
		this, // Contexto da Aplicação
		"Sucesso ao fazer algo", // Mensagem que será mostrada
		Toast.LENGHT_SHORT
	).show()
}
```

Não esquecer de chamar o .show() no final do Toast, se não ele não vai aparecer.

O Toast recebe 3 parâmetros:
- O Contexto da Activity;
- O Texto que será mostrado;
- A Duração da mensagem;

A duração pode ser:
- Toast.LENGTH_SHOT;
- Toast.LENGTH_LONG;