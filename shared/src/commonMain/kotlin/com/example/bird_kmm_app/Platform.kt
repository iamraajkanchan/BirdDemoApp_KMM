package com.example.bird_kmm_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform