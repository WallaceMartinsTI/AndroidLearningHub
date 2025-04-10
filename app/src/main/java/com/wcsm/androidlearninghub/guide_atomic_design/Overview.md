## Atomic Design

### Átomos

São os componentes mais básicos e reutilizáveis: botões, ícones, textos, inputs.
```kotlin
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge
    )
}
```

### Moléculas

São combinações de átomos. Ex: um campo de texto com label e botão.
```kotlin
@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(text = label)
        TextField(
            value = value,
            onValueChange = onValueChange
        )
    }
}
```

### Organismos

São agrupamentos mais complexos. Ex: um card de usuário com avatar, nome, botão.
```kotlin
@Composable
fun UserCard(
    name: String,
    email: String,
    onEditClick: () -> Unit
) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            TitleText(text = name)
            Text(text = email)
            Spacer(modifier = Modifier.height(8.dp))
            PrimaryButton(text = "Editar", onClick = onEditClick)
        }
    }
}
```

### Templates

Estrutura geral de uma tela com placeholders para conteúdo dinâmico.
```kotlin
@Composable
fun ProfileTemplate(
    header: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Column {
        header()
        Divider()
        content()
    }
}
```

### Páginas
Implementações específicas das telas, usando o template com conteúdo real.

```kotlin
@Composable
fun ProfilePage(
    name: String,
    email: String,
    onEditClick: () -> Unit
) {
    ProfileTemplate(
        header = { TitleText(text = "Perfil do Usuário") },
        content = {
            UserCard(name = name, email = email, onEditClick = onEditClick)
        }
    )
}

```

### Organização de pastas sugerida

```text
ui/
│
├── atoms/
│   └── PrimaryButton.kt, TitleText.kt
│
├── molecules/
│   └── LabeledTextField.kt
│
├── organisms/
│   └── UserCard.kt
│
├── templates/
│   └── ProfileTemplate.kt
│
├── pages/
│   └── ProfilePage.kt
```
