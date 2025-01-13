package com.lucas.application.ports.outputs

import com.lucas.infra.persistence.entities.ProductEntity
import com.seudominio.domain.entities.Product

interface ProductRepository {
    fun findAll(): List<Product>
    fun findById(id: Long): Product?
    fun save(product: Product): Product
    fun delete(id: Long)
    fun saveAll(products: MutableList<ProductEntity>)
}
