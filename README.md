# App03 - Grimório de Hogwarts

Aplicativo Android nativo desenvolvido em Kotlin para a disciplina **DS151 - Desenvolvimento para Dispositivos Móveis**, que permite consultar personagens e criaturas do universo de Harry Potter por meio da API pública [Hogwarts API](https://hogwarts-api.com/).

## Integrantes do Grupo
- **João Alberto François**  
  GRR20211640
- **Lucas Labendzs**
  GRR20245121
- **Kauan Calegari** GRR20243595
- **Renan Teles de Oliveira** GRR20246258

## Funcionalidades
- **Menu Principal (`MainActivity`):** Tela inicial com navegação para as funcionalidades de busca de personagens e criaturas.
- **Busca de Personagem (`PersonagemActivity`):**
  - Permite pesquisar por trecho do nome (ex.: `Weasley`).
  - Exibe **nome completo**, **espécie** e **casa** do personagem encontrado (exibindo `"Não informado"` caso espécie ou casa estejam ausentes).
  - Exibe `"Nenhum personagem encontrado"` quando não há resultados.
- **Busca de Criatura (`CriaturaActivity`):**
  - Permite pesquisar pelo nome completo da criatura.
  - Exibe o **nome** e a **imagem** da criatura com proporção preservada (sem distorção).
  - Exibe imagem substituta e o aviso `"Imagem indisponível"` caso a criatura não possua imagem ou ocorra falha no carregamento.
- **Validação e Tratamento de Erros:**
  - Validação antes das requisições (`trim()`, bloqueio de campo vazio e mínimo de 3 caracteres).
  - Barra de progresso horizontal (`ProgressBar`) exibida durante consultas à API e carregamento de imagens.
  - Tratamento para ausência de conexão, erros de servidor e resultados vazios sem encerrar o aplicativo.

## Tecnologias Utilizadas
- **Linguagem:** Kotlin
- **Plataforma:** Android Nativo (`minSdk 29` — Android 10)
- **Consumo de API:** Retrofit 2 + Gson
- **Assincronismo:** Kotlin Coroutines (`lifecycleScope`)
- **Carregamento de Imagens:** Picasso
- **Interface:** XML / ViewBinding / Material Components

## Estrutura do Projeto
```text
app/src/main/java/com/example/grimriodehogwarts/
├── MainActivity.kt          # Tela de menu principal
├── PersonagemActivity.kt    # Tela de busca e exibição de personagem
├── CriaturaActivity.kt      # Tela de busca e exibição de criatura
├── HogwartsApi.kt           # Interface de endpoints da Hogwarts API
├── RetrofitClient.kt        # Configuração singleton do cliente Retrofit
├── Models.kt                # Modelos de dados (Personagem, Criatura e resposta da API)
└── Validacao.kt             # Regras de validação de entrada e formatação de campos vazios
