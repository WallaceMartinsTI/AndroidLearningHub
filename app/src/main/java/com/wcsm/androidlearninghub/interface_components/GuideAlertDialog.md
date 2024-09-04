## AlertDialog

Cria-se uma caixa de Dialogo, onde você pode customizar o Título, a Mensagem e colocar Botões como 
"Cancelar" e "Confirmar" que ativam ações pré estabelecidas.

Exemplo:
```kotlin
private fun chamarDialogAlerta() {
    val alertBuilder = AlertDialog.Builder(this) // Passa o Contexto
    // Passar o Título do Dialog
    alertBuilder.setTitle("Confirmar exclusão do item?")
    // Passar a Mensagem do Dialog
    alertBuilder.setMessage("Tem certeza que quer remover?")
    // Botão para Ação Negativa
    // Dialog em Si, e a Posicao do Botão que está sendo clicado
    // Se não for usar os parâmetros, devem ser substituidos com underline ( _ )
    alertBuilder.setNegativeButton("Cancelar") { dialog, posicao ->
        // Se precisar esconder/liberar o Dialog:
        dialog.dismiss() // Mas não é necessário, pois já é feito de forma automática
    }
    alertBuilder.setPositiveButton("Remover") { _, __ ->
        // Remover por exemplo algo selecionado, como um item de uma lista
    }

    // Para obrigar o usuário a selecionar uma das opções dos botões
    alertBuilder.setCancelable(false)

    // Um terceiro botão "neutro", ex.: "Ajuda" para obter ajuda sobre o tema
    alertBuilder.setNeutralButton("ajuda") {
        // ...
    }

    alertBuilder.setIcon(/*caminho do ícone, ex: R.drawable.ic_alerta_24*/)

    // Após criar o Builder, deve-se criar o AlertDialog
    val alertDialog = alertBuilder.create()
    // Mostrar o Dialog
    alertDialog.show()
}
```

Forma Compactada:
```kotlin
AlertDialog.Builder(this)  
    .setTitle("Confirmar exclusão do item?")  
    .setMessage("Tem certeza que quer remover?")  
    .setNegativeButton("cancelar") {dialog, posicao ->  
	    Toast.makeText(this, "Cancelar ($posicao)", Toast.LENGTH_SHORT).show()  
        //dialog.dismiss()  
    }  
	.setPositiveButton("remover") {dialog, posicao ->  
        Toast.makeText(this, "Remover ($posicao)", Toast.LENGTH_SHORT).show()  
    }
    .setCancelable(false)  
    .create()  
    .show()
```
