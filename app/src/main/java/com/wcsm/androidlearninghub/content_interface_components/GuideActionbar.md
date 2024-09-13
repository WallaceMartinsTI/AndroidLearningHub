## ActionBar

### Criação

Vamos iniciar com a criação da ActionBar, você pode ativar ela no themes.xml.
```xml
// Se o seu tema estiver parecido com esse:
parent="Theme.Material3.DayNight.NoActionBar">

// Remove o ".NoActionBar":
parent="Theme.Material3.DayNight">

// Assim a ActionBar já vai aparecer, e seu título será o App Name.
```

### Ocultar Actionbar

É possível ocultar no themes.xml:
```xml
// De:
// parent="Theme.Material3.DayNight">
// Para:
parent="Theme.Material3.DayNight.NoActionBar">
```

E pode ocultar via código:
```kotlin
override fun onCreate(...) {
	// Oculta a ActionBar na tela desejada
	supportActionBar?.hide()
}
```
