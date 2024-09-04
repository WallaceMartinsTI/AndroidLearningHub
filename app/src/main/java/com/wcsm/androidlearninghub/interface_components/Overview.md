## Componentes de Interface

### Toast

Como criar um Toast:
```kotlin
import android.widget.Toast

Toast.makeText(
	this,
	"Mensagem aqui",
	Toast.LENGTH_LONG
).show()
```

Veja mais sobre Toast [clicando aqui](GuideToast.md).

### Snackbar

Como criar uma Snackbar:
```kotlin
import com.google.android.material.snackbar.Snackbar

val snackbar = Snackbar.make(
	view,
	"Mensagem aqui",
	Snackbar.LENGTH_SHORT
)
```

Veja mais sobre Snackbar [clicando aqui](GuideToast.md).