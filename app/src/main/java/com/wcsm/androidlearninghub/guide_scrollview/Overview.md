## Scrollview

O scrollview vai permitir o scroll (rolagem da tela) para exibir os conteúdos que não cabe no 
espaço do celular.

Permite que seja adicionado vários elementos dentro dele, podendo o usuário rolar para visualizar 
todos.

O scrollview deve ser parent(pai) dos elementos.

### Como utilizar

```
<scrollview>
    Elementos
    Elementos
</scrollview>
```

- Adicionando em Layout existente:
Após arrastar o componente de Scrollview para a tela, ir no código (xml) e passar o scrollview 
como elemento parent(pai).

- Adicionando no inicio do Layout:
Fazer a mesma modifficação acima, e também adicionar o atributo `android:fillViewport="true"`.