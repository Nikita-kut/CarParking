package model

sealed class Command(open val command: String) {
    abstract fun print()

    data class Start(override val command: String = "/start") : Command(command) {

        override fun print() {
            println(command)
        }
    }

    data class Help(override val command: String = "/help") : Command(command) {

        override fun print() {
            println(command)
        }
    }

    data class End(override val command: String = "/end") : Command(command) {

        override fun print() {
            println(print())
        }
    }
}