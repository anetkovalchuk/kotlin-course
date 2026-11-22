import kotlin.math.sqrt
import kotlin.random.Random

// Додаткове завдання (із зірочкою): sealed interface для результатів розв'язання
sealed interface Roots {
    data class TwoRoots(val x1: Double, val x2: Double) : Roots
    data class OneRoot(val x: Double) : Roots
    data object NoRealRoots : Roots
}

class QuadraticEquation(
    a: Double,
    var b: Double,
    var c: Double
) {
    // Вторинний конструктор для цілих коефіцієнтів
    constructor(a: Int, b: Int, c: Int) : this(
        a = a.toDouble(),
        b = b.toDouble(),
        c = c.toDouble()
    )

    // Вторинний конструктор для зведеного квадратного рівняння (a = 1.0)
    constructor(b: Double, c: Double) : this(
        a = 1.0,
        b = b,
        c = c
    )

    var a: Double = a
        set(value) {
            require(value != 0.0) { "Коефіцієнт 'a' не може бути нулем!" }
            field = value
        }

    init {
        require(a != 0.0) { "Коефіцієнт 'a' не може бути нулем!" }
    }

    // Використовуємо custom get(), а не збережене поле, бо при зміні a, b або c
    // значення дискримінанта має перераховуватися актуально ("на льоту")
    val discriminant: Double
        get() = b * b - 4.0 * a * c

    fun solve(): List<Double> {
        val d = discriminant
        return when {
            d > 0.0 -> {
                val sqrtD = sqrt(d)
                val x1 = (-b - sqrtD) / (2.0 * a)
                val x2 = (-b + sqrtD) / (2.0 * a)
                listOf(x1, x2)
            }
            d == 0.0 -> {
                val x = -b / (2.0 * a)
                listOf(x)
            }
            else -> emptyList()
        }
    }

    fun solveSealed(): Roots {
        val d = discriminant
        return when {
            d > 0.0 -> {
                val sqrtD = sqrt(d)
                Roots.TwoRoots(
                    x1 = (-b - sqrtD) / (2.0 * a),
                    x2 = (-b + sqrtD) / (2.0 * a)
                )
            }
            d == 0.0 -> Roots.OneRoot(-b / (2.0 * a))
            else -> Roots.NoRealRoots
        }
    }

    override fun toString(): String {
        val aPart = if (a == 1.0) "x^2" else "${a}x^2"
        val bPart = if (b < 0.0) "(${b})x" else "${b}x"
        return "$aPart + $bPart + $c = 0"
    }
}

fun main() {
    // Перевірка конструкторів, валідації та мутабельності
    val eq1 = QuadraticEquation(1.0, -5.0, 6.0)
    val eqFromInt = QuadraticEquation(2, -7, 3)
    val monicEq = QuadraticEquation(-3.0, 2.0)

    println("Рівняння 1: $eq1, D = ${eq1.discriminant}, корені = ${eq1.solve()}")
    println("З Int: $eqFromInt, корені = ${eqFromInt.solve()}")
    println("Зведене: $monicEq, корені = ${monicEq.solve()}")

    // Зміна b та c — discriminant перераховується автоматично через get()
    eq1.b = 4.0
    eq1.c = 4.0
    println("Після зміни b і c: $eq1, новий D = ${eq1.discriminant}, корені = ${eq1.solve()}")

    // Перевірка sealed interface Roots через when
    when (val rootsResult = QuadraticEquation(1.0, 2.0, 5.0).solveSealed()) {
        is Roots.TwoRoots -> println("Два корені: x1=${rootsResult.x1}, x2=${rootsResult.x2}")
        is Roots.OneRoot -> println("Один корінь: x=${rootsResult.x}")
        is Roots.NoRealRoots -> println("Дійсних коренів немає")
    }

    // Частина 4: 100 випадкових рівнянь із коефіцієнтами в [-50; 50], a != 0
    val equations = List(100) {
        var randA = Random.nextInt(-50, 51)
        while (randA == 0) {
            randA = Random.nextInt(-50, 51)
        }
        val randB = Random.nextInt(-50, 51).toDouble()
        val randC = Random.nextInt(-50, 51).toDouble()
        QuadraticEquation(randA.toDouble(), randB, randC)
    }

    val twoRootsEquations = equations.filter { it.discriminant > 0.0 }

    twoRootsEquations.forEach { eq ->
        println("$eq -> корені: ${eq.solve()}")
    }

    println("Загальна кількість рівнянь із 2 розв'язками: ${twoRootsEquations.size}")
}