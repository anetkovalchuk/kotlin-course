fun main() {
    val text = "abracadabra"
    val counts = text.groupingBy { it }.eachCount()

    counts.forEach { (char, count) ->
        println("'$char' -> $count")
    }
}