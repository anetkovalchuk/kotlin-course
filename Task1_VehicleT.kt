data class Vehicle(
    var brand: String = "",
    var model: String = "",
    var year: Int = 0,
    var licensePlate: String = ""
)

fun main() {
    // Завдання 1: Створення, налаштування (apply) та логування (also)
    val vehicle = Vehicle()
        .apply {
            brand = "Honda"
            model = "Civic"
            year = 2020
            licensePlate = "KA3344BI"
        }
        .also {
            println("Створено новий транспортний засіб: $it")
        }

    // Завдання 2: Форматований вивід даних через with
    with(vehicle) {
        println("Марка = $brand")
        println("Модель = $model")
        println("Рік випуску: $year")
        println("Номерний знак: $licensePlate")
    }
}