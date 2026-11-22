import kotlin.random.Random

fun main() {
    // 100 випадкових цілих чисел від -100 до 100 включно
    val numbers = IntArray(100) { Random.nextInt(-100, 101) }

    val processed = numbers
        .filter { it > 0 }            // лишити лише додатні числа
        .filter { it % 5 == 0 }       // лишити лише ті, що діляться на 5 без остачі
        .map { it * it }              // кожне піднести до квадрату
        .sortedDescending()           // відсортувати за спаданням

    if (processed.isNotEmpty()) {
        val maxVal = processed.maxOrNull()
        val minVal = processed.minOrNull()
        println("Найбільше: $maxVal, найменше: $minVal")
    } else {
        println("Після фільтрації колекція порожня")
    }

    val resultString = processed
        .map { it.toString() }        // перетворити в колекцію рядків
        .joinToString(", ")           // з'єднати в один рядок через кому

    println("Результат: $resultString")
}