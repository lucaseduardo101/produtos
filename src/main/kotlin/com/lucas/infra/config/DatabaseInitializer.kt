package com.lucas.infra.config

import com.lucas.application.ports.outputs.ProductRepository
import com.lucas.infra.persistence.entities.ProductEntity
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.random.Random

@Configuration
class DatabaseInitializer {

    @Bean
    fun initDatabase(productRepository: ProductRepository): CommandLineRunner {
        return CommandLineRunner {
            val products = mutableListOf<ProductEntity>()
            val names = listOf("Laptop", "Smartphone", "Headphones", "Tablet", "Smartwatch")
            val descriptions = listOf("High performance", "Latest model", "Noise cancelling", "Lightweight", "Fitness tracker")

            repeat(100) {
                val name = names[Random.nextInt(names.size)]
                val description = descriptions[Random.nextInt(descriptions.size)]
                val price = Random.nextDouble(100.0, 1000.0)
                products.add(ProductEntity(name = name, description = description, price = price))
            }

            productRepository.saveAll(products)
            println("Database initialized with ${products.size} products")
        }
    }
}
