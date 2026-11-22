// Частина 3: Звичайний клас із ручною реалізацією функціоналу data class
class Employee(
    val firstName: String,
    val lastName: String,
    val position: String
) {
    var bonus: Int = 0

    operator fun component1(): String = firstName
    operator fun component2(): String = lastName
    operator fun component3(): String = position

    fun copy(
        firstName: String = this.firstName,
        lastName: String = this.lastName,
        position: String = this.position
    ): Employee = Employee(firstName, lastName, position).also {
        it.bonus = this.bonus
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Employee) return false
        return firstName == other.firstName &&
                lastName == other.lastName &&
                position == other.position
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + position.hashCode()
        return result
    }

    override fun toString(): String {
        return "Employee(firstName='$firstName', lastName='$lastName', position='$position')"
    }
}

// Для порівняння з data class можна тимчасово винести окремо:
data class EmployeeData(
    val firstName: String,
    val lastName: String,
    val position: String
) {
    var bonus: Int = 0
}

fun main() {
    val emp1 = Employee("Олена", "Коваль", "Kotlin Developer")
    val emp2 = Employee("Олена", "Коваль", "Kotlin Developer")
    val emp3 = emp1

    println("emp1: $emp1")
    println("emp1 === emp2: ${emp1 === emp2}") // false — різні об'єкти в пам'яті
    println("emp1 == emp2: ${emp1 == emp2}")   // true — завдяки перевизначеному equals()
    println("emp1 === emp3: ${emp1 === emp3}") // true — одне й те саме посилання

    // Деструктуризація
    val (name, surname, pos) = emp1
    println("Деструктуризація: $name, $surname, $pos")

    // Копіювання зі зміною посади
    val promoted = emp1.copy(position = "Senior Kotlin Developer")
    println("Після copy: $promoted")

    // Перевірка властивості поза основним конструктором (var bonus)
    val d1 = EmployeeData("Іван", "Петренко", "QA").apply { bonus = 100 }
    val d2 = EmployeeData("Іван", "Петренко", "QA").apply { bonus = 500 }
    println("Data class d1 == d2 при різних bonus: ${d1 == d2}") // true, бо bonus не в первинному конструкторі
}