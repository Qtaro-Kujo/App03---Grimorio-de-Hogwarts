package com.example.grimriodehogwarts

import retrofit2.http.GET
import retrofit2.http.Query

interface HogwartsApi {

    @GET("api/characters")
    suspend fun buscarPersonagens(
        @Query("search") nome: String,
        @Query("page_size") pageSize: Int = 10
    ): ListaResponse<Personagem>

    @GET("api/creatures")
    suspend fun buscarCriaturas(
        @Query("search") nome: String,
        @Query("page_size") pageSize: Int = 20
    ): ListaResponse<Criatura>
}