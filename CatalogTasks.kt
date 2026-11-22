data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    val rating: Double,
    val inStock: Boolean
)

val products = listOf(
    Product(1, "Google Pixel 9", "Smartphones", 899.0, 4.8, true),
    Product(2, "iPhone 16 Pro", "Smartphones", 1199.0, 4.9, true),
    Product(3, "Galaxy A55", "Smartphones", 399.0, 4.3, false),
    Product(4, "MacBook Air M3", "Laptops", 1299.0, 4.9, true),
    Product(5, "ThinkPad X1 Carbon", "Laptops", 1499.0, 4.6, false),
    Product(6, "Dell XPS 13", "Laptops", 1150.0, 4.4, true),
    Product(7, "Sony WH-1000XM5", "Audio", 349.0, 4.7, true),
    Product(8, "AirPods Pro 2", "Audio", 249.0, 4.8, false),
    Product(9, "Pixel Buds Pro 2", "Audio", 229.0, 4.5, true)
)

// Частина 4: Власна функція вищого порядку (розширення для List<Product>)
fun List<Product>.filterAndTransform(
    predicate: (Product) -> Boolean,
    transform: (Product) -> String
): List<String> {
    val result = mutableListOf<String>()
    for (item in this) {
        if (predicate(item)) {
            result.add(transform(item))
        }
    }
    return result
}

fun main() {
    // Частина 1: Єдиний ланцюжок трансформації даних
    println("=== Частина 1 ===")
    products
        .filter { it.inStock && it.rating >= 4.7 && it.price < 1000.0 }
        .sortedByDescending { it.rating }
        .map { "Назва: ${it.name} | Рейтинг: ${it.rating} | Ціна: $${it.price}" }
        .forEach { println(it) }

    // Частина 2: Пошук та предикатні перевірки
    println("\n=== Частина 2 ===")
    val expensiveLaptopName = products
        .find { it.category == "Laptops" && it.price > 1200.0 }
        ?.name ?: "Товар не знайдено"
    println("Перший ноутбук дорожчий за 1200.0: $expensiveLaptopName")

    val hasExpensiveAudio = products.any { it.category == "Audio" && it.price > 300.0 }
    println("Є аудіопристрій дорожчий за 300.0: $hasExpensiveAudio")

    val allSmartphonesHighRated = products
        .filter { it.category == "Smartphones" }
        .all { it.rating > 4.0 }
    println("Усі смартфони мають рейтинг > 4.0: $allSmartphonesHighRated")

    // Частина 3: Розділення та групування вибірки
    println("\n=== Частина 3 ===")
    val (available, outOfStock) = products.partition { it.inStock }
    println("В наявності: ${available.size}, немає на складі: ${outOfStock.size}")

    val groupedByCategory: Map<String, List<Product>> = products.groupBy { it.category }
    groupedByCategory.forEach { (category, items) ->
        val topProduct = items.maxByOrNull { it.price }
        if (topProduct != null) {
            println("Категорія $category -> Найдорожчий: ${topProduct.name} ($${topProduct.price})")
        }
    }

    // Частина 4: Виклик filterAndTransform з Trailing Lambda
    println("\n=== Частина 4 ===")
    val promoMessages = products.filterAndTransform({ it.price < 300.0 }) { product ->
        "Акційна ціна на ${product.name}: лише $${product.price}!"
    }
    promoMessages.forEach { println(it) }

    // Додаткове завдання (із зірочкою): fold для товарів в наявності (без sumOf)
    println("\n=== Додаткове завдання (fold) ===")
    val inStockItems = products.filter { it.inStock }
    val totalInStockPrice = inStockItems.fold(0.0) { acc, product -> acc + product.price }
    val averageInStockPrice = if (inStockItems.isNotEmpty()) totalInStockPrice / inStockItems.size else 0.0
    println("Сумарна вартість товарів в наявності: $$totalInStockPrice")
    println("Середня вартість товару в наявності: $$averageInStockPrice")
}