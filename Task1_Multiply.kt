fun multiplyByAddition(a: Int, b: Int): Int {
    var result = 0
    repeat(b) {
        result += a
    }
    return result
}

fun main() {
    val a = 6
    val b = 7
    println("Добуток $a * $b = ${multiplyByAddition(a, b)}")

    // Додатковий приклад з forEach над колекцією повторень
    var viaForEach = 0
    List(b) { a }.forEach { viaForEach += it }
    println("Через forEach: $viaForEach")
}