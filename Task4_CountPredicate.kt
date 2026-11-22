fun countByPredicate(items: List<String>, predicate: (String) -> Boolean): Int {
    return items.count(predicate)
}

fun main() {
    val lines = listOf("abc", "a1b", "test123", "hello", "v2.0")

    // Приклад: кількість рядків, що містять принаймні одну цифру
    val withDigitCount = countByPredicate(lines) { str -> str.any { it.isDigit() } }
    println("Рядків із цифрами: $withDigitCount")
}