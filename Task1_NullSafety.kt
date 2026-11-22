data class Address(
    val street: String,
    val city: String?,
    val postalCode: String?
)

data class Client(
    val id: Int,
    val name: String,
    val email: String?,
    val address: Address?,
    val extraData: Any?
)

val clientList = listOf(
    Client(
        id = 1,
        name = "Олена",
        email = "olena@example.com",
        address = Address("вул. Саксаганського, 10", "Київ", "01033"),
        extraData = "VIP-клієнт"
    ),
    Client(
        id = 2,
        name = "Богдан",
        email = null,
        address = Address("вул. Городоцька, 45", "Львів", null),
        extraData = 42
    ),
    Client(
        id = 3,
        name = "Марія",
        email = "maria@example.com",
        address = null,
        extraData = null
    ),
    Client(
        id = 4,
        name = "Дмитро",
        email = null,
        address = Address("вул. Соборна, 1", null, null),
        extraData = "Очікує дзвінка"
    )
)

// Завдання 1: Ланцюжок безпечних викликів та Elvis-оператор (?. , ?:)
fun getShippingLabel(client: Client): String {
    val addr = client.address ?: return "Самовивіз: Клієнт ${client.name} не надав адреси"
    val cityName = addr.city ?: "Місто не вказано"
    val postal = addr.postalCode ?: "Індекс невідомий"
    return "${addr.street}, $cityName, $postal"
}

// Завдання 2: Безпечне приведення типів (as?) та Smart Cast
fun printClientNote(client: Client) {
    val note = client.extraData as? String ?: "Додаткові примітки відсутні"
    println("Клієнт ${client.name} -> Примітка: $note")
}

// Завдання 4: Валідація з викиданням винятку (?: throw ... або requireNotNull)
fun getClientEmailOrThrow(client: Client): String {
    return client.email ?: throw IllegalArgumentException("Клієнт з ID ${client.id} не має електронної пошти!")
}

// Завдання 5: Дослідження оператора !!
// Оператор !! варто використовувати лише тоді, коли значення гарантовано не null
// за інваріантом програми або контрактом і ми свідомо хочемо миттєвий краш (NPE)
// при порушенні цього контракту (наприклад, одразу після явно перевіреного стану або в тестах).
fun forceGetPostalCode(client: Client): String {
    return client.address!!.postalCode!!
}

fun main() {
    println("--- Завдання 1 ---")
    println("Адреса Олена: ${getShippingLabel(clientList[0])}")
    println("Адреса Богдан: ${getShippingLabel(clientList[1])}")
    println("Адреса Марія: ${getShippingLabel(clientList[2])}")

    println("\n--- Завдання 2 ---")
    clientList.forEach { printClientNote(it) }

    println("\n--- Завдання 3 ---")
    val validEmails: List<String> = clientList.map { it.email }.filterNotNull()
    val shortestEmailLength = validEmails.minOfOrNull { it.length }
    println("База email для розсилки: $validEmails")
    println("Довжина найкоротшого email: $shortestEmailLength")

    println("\n--- Завдання 4 ---")
    try {
        getClientEmailOrThrow(clientList[1]) // Богдан, id = 2, email = null
    } catch (e: IllegalArgumentException) {
        println("Перехоплено виняток: ${e.message}")
    }

    println("\n--- Завдання 5 ---")
    println("Індекс клієнта Олена: ${forceGetPostalCode(clientList[0])}")
    try {
        forceGetPostalCode(clientList[1]) // Богдан, id = 2, postalCode = null
    } catch (e: NullPointerException) {
        println("Перехоплено очікуваний NPE: ${e.message}")
    }
}