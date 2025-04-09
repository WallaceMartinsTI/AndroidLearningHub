## Koin

Koin é um framework de injeção de dependência (DI) leve e pragmático para Kotlin. Ele é fácil de 
configurar, não usa code generation, e é 100% escrito em Kotlin — ideal para projetos Android 
modernos, especialmente com Jetpack Compose.

### Por que usar Koin?

- Facilita a *injeção de dependências* sem a complexidade de anotações ou processos de build extras;
- Suporta *ViewModels*, *Singletons*, *qualificadores*, entre outros;
- Integra-se perfeitamente com *Android*, *Jetpack Compose*, e outras bibliotecas populares como Retrofit.

### Estrutura de Módulos

1. coreModule
Responsável por fornecer dependências reutilizáveis em todo o app, como SharedPreferences, 
repositórios comuns e ViewModels globais.

```kotlin
val coreModule = module {
    single {
        androidContext().getSharedPreferences(
            "pref", Context.MODE_PRIVATE
        )
    }

    single<Repository>(qualifier = named("RepositoryImpl2")) {
        RepositoryImpl2(get())
    }

    viewModel {
        KoinViewModel(
            get(named("RepositoryImpl2"))
        )
    }
}
```

2. featureModule
Contém dependências específicas da feature, como a instância da API (Retrofit), o repositório da 
feature e o ViewModel da tela.

```kotlin
val featureModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(SomeApi.BASE_URL)
            .build()
            .create(SomeApi::class.java)
    }

    single<Repository>(qualifier = named("RepositoryImpl")) { RepositoryImpl(get()) }

    viewModel {
        FeatureViewModel(
            get(named("RepositoryImpl"))
        )
    }
}
```

### Inicialização

A configuração do Koin é feita na classe *Application*, garantindo que os módulos sejam carregados 
assim que o app iniciar:

```kotlin
class AndroidLearningHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AndroidLearningHubApplication)
            modules(
                coreModule,
                featureModule
            )
        }
    }
}
```

### Dependências no *build.gradle*

```kotlin
// Base do Koin para Android
implementation("io.insert-koin:koin-android:3.5.3")

// Para suporte a ViewModel no Compose
implementation("io.insert-koin:koin-androidx-compose:3.5.3")
```

### Dicas

- Use *named(...)* para distinguir múltiplas implementações do mesmo tipo;
- ViewModels podem ser injetados diretamente em Composables com *koinViewModel()*;
- É possível organizar módulos por features para melhor escalabilidade;