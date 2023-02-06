fun main() {
    while (true) {
        println(
            """Введи '$START_COMMAND_1' для начала работы
                |Введи '$END_COMMAND_1' для завершения работы """.trimMargin()
        )
        when (readLine()) {
            START_COMMAND_1, START_COMMAND_2 -> if (!startBlock()) return
            END_COMMAND_1, END_COMMAND_2 -> if (!endBlock()) return
            else -> println("Неправильная команда для начала работы")
        }
    }
}

private fun startBlock(): Boolean {
    println(
        """Привет!
                |Для того, чтобы узнать что я умею, введи '$HELP_COMMAND_1'
                |Чтобы закончить работу программы, введи '$END_COMMAND_1' """.trimMargin()
    )
    while (true) {
        when (readLine()) {
            HELP_COMMAND_1, HELP_COMMAND_2 -> println(
                """Доступные команды:
                            |/start - начало работы программы
                            |/end - завершение работы программы """.trimMargin()
            )
            END_COMMAND_1, END_COMMAND_2 -> if (!endBlock()) return false
            START_COMMAND_1, START_COMMAND_2 -> println("Программа уже запущена")
            else -> println("Введена неправильная команда, введите '$HELP_COMMAND_1' для того чтобы продолжить работу или '$END_COMMAND_1' чтобы завершить работу")

        }
    }
}

private fun endBlock(): Boolean {
    println("Программа заканчивает свою работу, пока!")
    return false
}

private const val START_COMMAND_1 = "/start"
private const val START_COMMAND_2 = "start"

private const val HELP_COMMAND_1 = "/help"
private const val HELP_COMMAND_2 = "help"

private const val END_COMMAND_1 = "/end"
private const val END_COMMAND_2 = "end"