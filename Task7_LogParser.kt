fun main() {
    val rawLogs = listOf(
        "2026-09-01 INFO: User logged in",
        "2026-09-01 ERROR: 500 Internal Server Error",
        "2026-09-01 WARN: High memory usage",
        "2026-09-01 ERROR: 404 Not Found",
        "2026-09-01 INFO: Payment processed"
    )

    var infoCount = 0
    var warnCount = 0
    var errorCount = 0
    val errorLogs = mutableListOf<String>()

    for (line in rawLogs) {
        when {
            line.contains("INFO") -> infoCount++
            line.contains("WARN") -> warnCount++
            line.contains("ERROR") -> {
                errorCount++
                errorLogs.add(line)
            }
        }
    }

    val errorsBlock = if (errorLogs.isEmpty()) {
        "  (немає помилок)"
    } else {
        errorLogs.joinToString(separator = "\n") { "  - $it" }
    }

    val report = """
        === Звіт аналізу логів ===
        Всього записів: ${rawLogs.size}
        INFO:  $infoCount
        WARN:  $warnCount
        ERROR: $errorCount

        Список помилок (ERROR):
        $errorsBlock
    """.trimIndent()

    println(report)
}