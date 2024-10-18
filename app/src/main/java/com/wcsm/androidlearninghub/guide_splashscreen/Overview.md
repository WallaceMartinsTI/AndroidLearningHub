## Splash Screen

A Splashscreen consiste em uma tela inicial que geralmente aparece quando o aplicativo está 
sendo iniciado.

### Como Utilizar

Configure uma cor, layout, e depois o seguinte código:
```kotlin
Handler(Looper.getMainLooper()).postDelayed({
    val intent = Intent(this, MainActivity::class.java) // MainActivity -> Tela inicial do seu app
    startActivity(intent)
    finish()
}, 3000)
```