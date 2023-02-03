package com.route.todoappc_37.designPatterns

import android.util.Log

class Main2 {
    /*
        Creational Pattern -> Singleton - Factory
        Factory ->
           Product ->
                   Food
                   Drinks
                   Cleaning

        // Sturctural Patterns - Behavioral Patterns

     */


}

open class Product {
    var name: String? = null
}

class Food : Product() {


}

class Drinks : Product() {

}

class Cleaning : Product() {


}

class ProductFactory {
    val productsRegistered: HashMap<String, Product> = HashMap<String, Product>()

    fun registerProduct(name: String, value: Product) {
        productsRegistered.put(name, value)

    }

    fun getProductByName(name: String): Product? {
        if (productsRegistered.containsKey(name)) {
            return productsRegistered.get(name)
        }
        return null
    }
}

class Main {

    fun doWorkOnProductFactory() {
        val productFactory = ProductFactory()
        productFactory.registerProduct("food", Food())
        productFactory.registerProduct("cleaning", Cleaning())
        productFactory.registerProduct("drinks", Drinks())
        productFactory.registerProduct("product", Product())
        val food = productFactory.getProductByName("food")
        val product = productFactory.getProductByName("product")
        food?.apply {
            name = "Food"
        }
        Log.e("TAG", "FOOD = $food")
        Log.e("TAG", "product = $product")

    }
}

