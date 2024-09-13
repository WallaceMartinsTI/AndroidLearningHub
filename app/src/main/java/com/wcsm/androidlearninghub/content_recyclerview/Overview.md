## Recycler View

Para trabalhar com RecyclerView será necessário um Adapterm ViewHolder e um LayoutManager.

### Criação e Utilização

1. Criar a RecyclerView no Layout e colocar seu ID, exemplo: rv_lista (é possível clicar com o botão
   direito e clicar em Set Sample Data para ver dados de demonstração);

2. Referênciar a lista:

```kotlin
private lateinit var rvList: RecyclerView

...onCreate() {
    ...
    rvLista = findViewById(R.id.rv_lista)
    ...
}
```

3. Criar o Layout para o Item da lista:
   - Botão direito em layout;
   - Clique em new;
   - Selecione Layout Resource File ;
   - Coloque o nome do arquivo e clique em ok.

Após criar o arquivo, faça o Layout e quando terminar, ajustar se necessário o layout_width e
layout_height do PAI (ConstraintLayout).

4. Criar o RecyclerView Adapter: [Recycler view adapter](RecyclerViewAdapter.md)

5. Configurar o LayoutManager, sendo eles:

- LinearLayoutManager: (Mais Utilizaddo)

```kotlin
rvLista.layoutManager = LinearLayoutManager(
    this,
    RecyclerView.VERTICAL,
    false
)
```

- GridLayoutManager:

```kotlin
rvLista.layoutManager = GridLayoutManager(
    this,
    2
)
```

- StaggeredGridLayout:

```kotlin
rvLista.layoutManager = StaggeredGridLayoutManager(
    2,
    RecyclerView.VERTICAL
)
```
