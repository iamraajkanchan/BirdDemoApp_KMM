package com.example.bird_kmm_app.android.repository

import com.example.bird_kmm_app.android.model.BirdResponse
import com.example.bird_kmm_app.android.model.TodosResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

class AppRepository private constructor() {
    private val httpClient = HttpClient() { install(ContentNegotiation) { json() } }

    suspend fun getBirds(): List<BirdResponse> {
        return httpClient.get("https://sebastianaigner.github.io/demo-image-api/pictures.json")
            .body()
    }

    suspend fun getTodos(): List<TodosResponse> {
        return httpClient.get("https://jsonplaceholder.typicode.com/todos").body()
    }

    fun shutHttpClient() {
        httpClient.close()
    }

    companion object {
        fun getInstance(): AppRepository = AppRepository()
    }
}