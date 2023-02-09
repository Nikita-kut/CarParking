import domain.ManagerImpl
import model.*
import model.Car.Companion.toCar
import model.Command.*

fun main() {

    val parking = Parking(maxParkingSize = 30)
    val manager = ManagerImpl(parking)

    val start = Start()
    val help = Help()
    val park = Park()
    val _return = Return()
    val infoByCar = InfoByCar()
    val infoByPlace = InfoByPlace()
    val currentStats = CurrentStats()
    val allStats = AllStats()

    start.print()
    while (true) {
        if (readLine() == start.command) {
            help.print()
            while (true) {
                when (readLine()) {
                    help.command -> help.print()
                    park.command -> {
                        while (true) {
                            if (manager.canParkCar) {
                                park.print()
                                val parkingInfo = readLine().toString().split(" ")
                                if (parkingInfo.size == 4) {
                                    manager.parkCar(parkingInfo.toCar())
                                    break
                                } else {
                                    park.printIncorrectInput()
                                }
                            } else {
                                park.printFullParkingCase()
                                return
                            }
                        }
                    }

                    _return.command -> {
                        _return.print()
                        while (true) {
                            if (manager.returnCar(readLine().toString())) break
                        }
                    }

                    infoByCar.command -> {
                        infoByCar.print()
                        while (true) {
                            if (!manager.getInfoByCar(readLine().toString())) infoByCar.printNotFoundCar()
                            break
                        }
                    }

                    infoByPlace.command -> {
                        infoByPlace.print()
                        while (true) {
                            val place = readLine()?.toIntOrNull()
                            if (place != null) {
                                if (place > 0) {
                                    if (!manager.getInfoByPlace(place)) infoByPlace.printNotFoundCarCase()
                                    break
                                } else {
                                    infoByPlace.printNeedPositiveIntInputCase()
                                }
                            } else {
                                infoByPlace.printNeedIntCase()
                            }
                        }
                    }

                    currentStats.command -> manager.getCurrentStats()

                    allStats.command -> manager.getAllStats()

                    else -> {
                        println("Некорректная команда")
                    }
                }
            }
        } else {
            println("Неопознанная команда, для начала работы введи ${start.command}")
        }
    }
}