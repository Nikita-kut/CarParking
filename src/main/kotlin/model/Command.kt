package model

sealed class Command(open val command: String) {
    abstract fun print()

    data class Start(override val command: String = START) : Command(command) {

        override fun print() {
            println("Привет! Для начала работы введи $command")
        }
    }

    data class Help(override val command: String = HELP) : Command(command) {

        override fun print() {
            println(
                """
                Доступные команды:
                $PARK - запарковать автомобиль на любое свободное место
                $RETURN - забрать автомобиль с парковки
                $INFO_BY_CAR - получить место авто на парковке
                $INFO_BY_PLACE - получить инфу о машине по парковочному месту
                $CURRENT_STATS - узнать текущую загрузку парковки
                $ALL_STATS - статистика по парковке за всё время
            """.trimIndent()
            )
        }
    }

    data class End(override val command: String = END) : Command(command) {

        override fun print() {
            println(print())
        }
    }

    data class Park(override val command: String = PARK) : Command(command) {

        override fun print() {
            println("Чтобы запарковать автомобиль, введите: Марку | Номер | Цвет | Имя владельца")
        }

        fun printIncorrectInput() {
            println("Некорректная информация для парковки, повторите попытку")
        }

        fun printFullParkingCase() {
            println("Парковка переполнена, не можем припарковать автомобиль")
        }
    }

    data class Return(override val command: String = RETURN) : Command(command) {

        override fun print() {
            println("Введи имя владельца")
        }
    }

    data class InfoByCar(override val command: String = INFO_BY_CAR) : Command(command) {

        override fun print() {
            println("Для получения парковочного места введи номер автомобиля")
        }

        fun printNotFoundCar() {
            println("Автомобиль по данному номеру не найден, введи другой номер автомобиля")
        }
    }

    data class InfoByPlace(override val command: String = INFO_BY_PLACE) : Command(command) {

        override fun print() {
            println("Для получения информация об автомобиле введи номер парковочного места в целочисленном формате")
        }

        fun printNotFoundCarCase() {
            println("В данном месте никто не припаркован")
        }

        fun printNeedPositiveIntInputCase() {
            println("Номер парковочного места должен быть положительным и больше 0, повтори ввод")
        }

        fun printNeedIntCase() {
            println("Введи номер парковочного места в целочисленном формате")
        }
    }

    data class CurrentStats(override val command: String = CURRENT_STATS) : Command(command) {

        override fun print() {
            TODO("Not yet implemented")
        }
    }

    data class AllStats(override val command: String = ALL_STATS) : Command(command) {

        override fun print() {
            TODO("Not yet implemented")
        }
    }

    private companion object {
        const val START = "/start"
        const val HELP = "/help"
        const val END = "/end"
        const val PARK = "/park"
        const val RETURN = "/return"
        const val INFO_BY_CAR = "/park_info_by_car"
        const val INFO_BY_PLACE = "/park_info_by_place"
        const val CURRENT_STATS = "/park_stats"
        const val ALL_STATS = "/park_all_stats"
    }
}