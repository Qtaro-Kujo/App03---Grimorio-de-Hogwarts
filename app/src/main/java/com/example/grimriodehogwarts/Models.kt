package com.example.grimoriodehogwarts

data class ListaResponse<T>(
    val data: List<T>?
)

data class Personagem(
    val name: String?,
    val species: String?,
    val house: String?
)

data class Criatura(
    val name: String?,
    val image: String?
)