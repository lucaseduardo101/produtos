package com.seudominio.domain.entities

data class Product(
    val id: Long = 0,
    val name: String,
    val description: String,
    val price: Double
)
