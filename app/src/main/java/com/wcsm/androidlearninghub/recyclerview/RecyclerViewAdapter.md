## RecyclerView Adapter

### Introdução

O adapter vai adaptar a visualização para cada item da lista desta forma, deve ser passado uma 
lista para o Adapter. Na activity principal temos:
```kotlin
private lateinit var rvLista: RecyclerView

override fun onCreate(savedInstanceState: Bundle`?) {
	super.onCreate(savedInstanceState)
	setContentView(R.layout.activity_recyclerview)

	val lista = mutableListOf(
		Mensagem("jamilton", "Olá, tudo bem?", "10:45"),  
		Mensagem("ana", "Te vi ontem", "00:45"),  
		Mensagem("maria", "Não acredito...", "06:03"),  
		Mensagem("pedro", "Futebol hoje?", "15:32")
	)

	rvLista = findViewById(R.id.rv_lista)
	btnClique = findViewById(R.id.btn_clique)
	rvLista.adapter = MensagemAdapter(lista)
	rvLista.layoutManager = LinearLayoutManager(this)
}
```

Para melhor entendimento, nestes exemplos, vamos utilizar a Data Class Mensagem:
```kotlin
data class Mensagem(
	val nome: String,
	val ultima: String,
	val horario: String
)
```

### Classe Mensagem Addapter

Configuração Inicial, criar o Adapter e herdar da classe Adapter (Adapter<VH>)
```kotlin
import androidx.recyclerview.widget.RecyclerView.Adapter

class MensagemAdapter : Adapter<>() {}
```

Criar a ViewHolder:
```kotlin
import android.view.View
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MensagemAdapter : Adapter<MensagemAdapter.ViewHolder>() {

	inner class MensagemViewHolder(
		val itemView: View
	) : ViewHolder(itemView) {
	
	}

}
```

Implementar membros da Classe Herdada (Adapter):
```kotlin
import android.view.View
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MensagemAdapter() : Adapter<MensagemAdapter.ViewHolder>() {

	inner class MensagemViewHolder(
		val itemView: View
	) : ViewHolder(itemView) {
		val textNome: TextView = itemView.findViewById(R.id.text_nome)
		val textUltima: TextView = itemView.findViewById(R.id.text_ultima)
		val textHorario: TextView = itemView.findViewById(R.id.text_horario)
		
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHolder {}

	override fun onBindViewHolder(holder: MensagemViewHolder, position: Int) {}

	override fun getItemCount(): Int {}

}
```

Configuração inicial do Adapter
```kotlin
import android.view.View
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MensagemAdapter(
	private val lista: List<String>
) : Adapter<MensagemAdapter.ViewHolder>() {

	inner class MensagemViewHolder(
		val itemView: View
	) : ViewHolder(itemView) {
		val textNome: TextView = itemView.findViewById(R.id.text_nome)
		val textUltima: TextView = itemView.findViewById(R.id.text_ultima)
		val textHorario: TextView = itemView.findViewById(R.id.text_horario)
		
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHolder {

		val layoutInflater = LayoutInflater.from(parent.context)
		val itemView = layoutInflater.inflate(
			R.item_cardview, parent, false
		)
	
		return MensagemViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: MensagemViewHolder, position: Int) {
		val nome = lista[position]
		holder.textNome.text = nome
	}

	override fun getItemCount(): Int {
		return lista.size
	}

}
```

### ViewHolder

A visualização gerada (cada item da lista) é associada a uma Classe, que a chamamos de View Holder. 
Ao criar a classe ViewHolder, ela necessita de uma View, que deve ser passada.

Aqui também é onde criamos o método responsável por favor o bind dos dados no método implementado 
onBindViewHolder.

Exemplo (utilizando viewBinding):
```kotlin
inner class MensagemViewHolder(  
    val binding: ItemCardviewBinding  
) : ViewHolder(binding.root) {  

    fun bind(mensagem: Mensagem) {//Conectar com a interface  
        binding.textCardNome.text = mensagem.nome  
        binding.textCardUltima.text = mensagem.ultima  
        //holder.textHorario.text = mensagem.horario  
  
        // Aplicar eventos de clique  
        itemView.setOnClickListener {  
            clique(mensagem.nome)  
        }  
    }  
}
```

### Métodos Implementados

- onCreateViewHolder
O método onCreateViewHolder (Seria algo tipo: Ao criar o View Holder) vai criar a visualização. É 
necessário construir um LayoutInflater (vai construir o objeto LayoutInflater a partir de um 
Contexto):
```kotlin
val layoutInflater = LayoutInflater.from(parent.context)
```

Após criar o LayoutInflater será possível criar a View:
```kotlin
val itemView = layoutInflater(
	R.layout.item_lista, // O que eu vou inflar? O que vai ser construído de visualização?
	parent, // Deve passar o parent, que vem do ViewGroup
	false // attachToRoot
)
```

- onBindViewHolder
O método onBindViewHolder() (Seria tipo: Ao vincular os dados para a visualização) pega os dados que 
não temos na lista ainda e vincula a cada item de lista.
```kotlin
val nome = lista[position]
```

- getItemCount
O método getItemCount() (Seria algo tipo: Recuperar a quantidade de itens) vai contar a quantidade 
de itens.

### Evento de CLique em Cada Item da Lista

Primeiramente vamos criar um método na classe ViewHolder para fazer o bind.

#### Fazer o onClick direto no Adapter
```kotlin
import android.view.View
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MensagemAdapter(
	private val lista: List<String>
) : Adapter<MensagemAdapter.ViewHolder>() {

	inner class MensagemViewHolder(
		val itemView: View
	) : ViewHolder(itemView) {
		val textNome: TextView = itemView.findViewById(R.id.text_nome)
		val textUltima: TextView = itemView.findViewById(R.id.text_ultima)
		val textHorario: TextView = itemView.findViewById(R.id.text_horario)
		
		fun bind(mensagem: Mensagem) { // Bind = Conectar
			textNome.text = mensagem.nome
			textUltima.text = mensagem.text
			itemView.setOnClickListener {
				// Faz algo ao clicar no item da Lista
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHolder {

		val layoutInflater = LayoutInflater.from(parent.context)
		val itemView = layoutInflater.inflate(
			R.item_cardview, parent, false
		)
	
		return MensagemViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: MensagemViewHolder, position: Int) {
		val mensagem = lista[position]
		holder.bind(mensagem)
	}

	override fun getItemCount(): Int {
		return lista.size
	}

}
```

#### Fazer o onClick na Activity Pai
```kotlin
import android.view.View
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MensagemAdapter(
	private val lista: List<Mensagem>,
	private val clique: () -> Unit
) : Adapter<MensagemAdapter.ViewHolder>() {

	inner class MensagemViewHolder(
		val itemView: View
	) : ViewHolder(itemView) {
		val textNome: TextView = itemView.findViewById(R.id.text_nome)
		val textUltima: TextView = itemView.findViewById(R.id.text_ultima)
		val textHorario: TextView = itemView.findViewById(R.id.text_horario)
		
		fun bind(mensagem: Mensagem) { // Bind = Conectar
			textNome.text = mensagem.nome
			textUltima.text = mensagem.text
			itemView.setOnClickListener {
				// Faz algo ao clicar no item da Lista
				// Como chamar a função passada pro Adapter pelo Pai
				clique()
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHolder {

		val layoutInflater = LayoutInflater.from(parent.context)
		val itemView = layoutInflater.inflate(
			R.item_cardview, parent, false
		)
	
		return MensagemViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: MensagemViewHolder, position: Int) {
		val mensagem = lista[position]
		holder.bind(mensagem)
	}

	override fun getItemCount(): Int {
		return lista.size
	}

}
```

Alterações necessárias na Activity Pai (que chama o adapter):
```kotlin
// Se o último parâmetro for função, posso chamar fora dos parêntereses (Lambda)
rvLista.adapter = MensagemAdapter(lista) {
	// Função que será passada para o adapter, assim eu posso configurar para
	// chamar ela no click do item da lista, ou em um botão específico...
}
```

### Notificação de Mudança de Dados no RecyclerView

Devemos notificar mudança nos dados dentro do RecyclerView, e chamamos esse Conjunto de Dados de 
Dataset.

Precisamos notificar as mudanças de dados no RecyclerView.

O RecyclerView não vai ficar verificando o conjunto de dados a todo momento, apenas quando precisar 
exibir ou reciclar as views.

SEMPRE que houverem mudanças nos dados é necessário notificar o Adapter

#### Métodos de Notificação

NotifyDatasetChanged: o método notifyDataSetChanged() notifica o Adapter que todo o conjunto de 
dados precisa ser atualizado.
```kotlin
// ...
class MensagemAdapter(
	// private val lista: List<Mensagem>, -> Removo a lista
	private val clique: () -> Unit
) : Adapter<MensagemAdapter.ViewHolder>() {

	private var listaMensagens = mutableListOf<Mensagem>()

	fun atualizarListaDados( lista: MutableList<Mensagem> ) {
		listaMensagens = lista
		
		// Recarrega TODA a Lista novamente, assim atualizando ela na interface:
		notifyDataSetChanged()
	}

	inner class MensagemViewHolder ...
// ...
}

// Na Activity Pai
private lateinit var mensagemAdapter: MensagemAdapter

override fun OnCreate...{
	val lista = mutableListOf(
			Mensagem("jamilton", "Olá, tudo bem?", "10:45"),  
			Mensagem("ana", "Te vi ontem", "00:45"),  
			Mensagem("maria", "Não acredito...", "06:03"),  
			Mensagem("pedro", "Futebol hoje?", "15:32")
		)
	
	mensagemAdapter = MensagemAdapter {
		// Função "clique" que vai ser executada no Adapter
	}

	mensagemAdapter.atualizarListaDados(lista)
	rvLista.adapter = mensagemAdapter

	// Simulando o click em um botão para adicionar um novo elemento na Lista
	btnClique.setOnClickListener {
		lista.add(
			Mensagem("Nova Jamilton", "teste", "17:12")
		)
		mensagemAdapter.atualizarListaDados(lista)
	}
}

```

### Utilizando ViewBinding no Adapter

Atualizando o RecyclerView e o Adapter para ViewBinding:

#### Configuração na Activity
```kotlin
private val binding by lazy {
	ActivityRecyclerviewBinding.inflate(layoutInflater)
}

override fun onCreate(savedInstanceState: Bundle`?) {
	super.onCreate(savedInstanceState)
	setContentView(binding.root)

	// ...

	//rvLista = findViewById(R.id.rv_lista)
	//btnClique = findViewById(R.id.btn_clique)
	
	binding.rvLista.adapter = MensagemAdapter(lista)
	binding.rvLista.layoutManager = LinearLayoutManager(this)
	binding.btnClick.setOnClickListener {
		// ...
	}
}
```

#### Configuração no Adapter
```kotlin
class MensagemAdapter(
	private val lista: List<Mensagem>,
	private val clique: () -> Unit
) : Adapter<MensagemAdapter.ViewHolder>() {

	inner class MensagemViewHolder(
		val binding: ItemCardviewBinding
	) : ViewHolder(binding.root) {
		//val textNome: TextView = itemView.findViewById(R.id.text_nome)
		//val textUltima: TextView = itemView.findViewById(R.id.text_ultima)
		//val textHorario: TextView = itemView.findViewById(R.id.text_horario)
		
		fun bind(mensagem: Mensagem) { // Bind = Conectar
			binding.textNome.text = mensagem.nome
			binding.textUltima.text = mensagem.text
			itemView.setOnClickListener {
				// Faz algo ao clicar no item da Lista
				// Como chamar a função passada pro Adapter pelo Pai
				clique()
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHolder {

		val layoutInflater = LayoutInflater.from(parent.context)
		
		// val itemView = layoutInflater.inflate(
		// 	 R.item_cardview, parent, false
		// )
		val itemView = ItemCardviewBinding.inflate(
			layoutInflater, parent, false
		)
	
		return MensagemViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: MensagemViewHolder, position: Int) {
		val mensagem = lista[position]
		holder.bind(mensagem)
	}

	override fun getItemCount(): Int {
		return lista.size
	}

}
```
