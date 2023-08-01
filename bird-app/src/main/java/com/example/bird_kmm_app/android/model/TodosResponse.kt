package com.example.bird_kmm_app.android.model

import kotlinx.serialization.Serializable

@Serializable
data class TodosResponse(
	val id: Int? = null,
	val completed: Boolean? = null,
	val title: String? = null,
	val userId: Int? = null
)

