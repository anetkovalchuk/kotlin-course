fun main() {
    // 1. Помножити кожен елемент списку на фактор
    val actions = listOf("title", "year", "author")
    val prefix = "https://example.com"
    val id = 5
    val urls = actions.map { action -> "$prefix/$id/$action" }
    println(urls)

    // 2. Фільтрація елементів за лямбда-виразом
    val numbers = listOf(1, -2, 3, -4, 5, -6)
    val positives = numbers.filter { x -> x > 0 }
    val negatives = numbers.filter { x -> x < 0 }
    println("Positives: $positives")
    println("Negatives: $negatives")
}