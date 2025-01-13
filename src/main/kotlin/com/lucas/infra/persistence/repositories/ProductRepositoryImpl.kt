package com.lucas.infra.persistence.repositories

import com.lucas.application.ports.outputs.ProductRepository
import com.seudominio.domain.entities.Product
import com.lucas.infra.persistence.entities.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(private val jpaRepository: JpaProductRepository) : ProductRepository {

    override fun findAll(): List<Product> =
        jpaRepository.findAll().map { it.toDomain() }

    override fun findById(id: Long): Product? =
        jpaRepository.findById(id).orElse(null)?.toDomain()

    override fun save(product: Product): Product =
        jpaRepository.save(product.toEntity()).toDomain()

    override fun delete(id: Long) =
        jpaRepository.deleteById(id)

    override fun saveAll(products: MutableList<ProductEntity>) {
        jpaRepository.saveAll(products)
    }

    private fun ProductEntity.toDomain() = Product(id, name, description, price)
    private fun Product.toEntity() = ProductEntity(id, name, description, price)
}

interface JpaProductRepository : JpaRepository<ProductEntity, Long>
