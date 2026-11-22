fun stringFilter(items: List<String>, predicate: (String) -> Boolean): List<String> {
    return items.filter(predicate)
}

fun main() {
    val words = listOf("kotlin", "java", "coroutines", "android", "jet")
    val longWords = stringFilter(words) { it.length >= 6 }
    println("Довгі слова: $longWords")
}