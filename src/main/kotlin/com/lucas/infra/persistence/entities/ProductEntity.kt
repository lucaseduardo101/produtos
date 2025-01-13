package com.lucas.infra.persistence.entities

import javax.persistence.*

@Entity
@Table(name = "products")
data class ProductEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    var price: Double
)
