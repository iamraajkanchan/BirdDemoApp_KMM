package com.example.bird_kmm_app.android.repository

import com.example.bird_kmm_app.android.model.BirdResponse
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

    fun shutHttpClient() {
        httpClient.close()
    }

    companion object {
        fun getInstance(): AppRepository = AppRepository()
    }
}