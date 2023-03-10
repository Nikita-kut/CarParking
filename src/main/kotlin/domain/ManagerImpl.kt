package domain

import model.Car
import model.Parking
import kotlin.random.Random

class ManagerImpl(override val parking: Parking) : Manager {
    override val canParkCar: Boolean
        get() = parking.amountCarsOnParking < parking.maxParkingSize

    private var parkingCounter = 0

    override fun parkCar(car: Car) {
        while (true) {
            val randomPlace = Random.nextInt(parking.maxParkingSize + 1)
            if (!parking.cars.filterValues { it != null }.keys.contains(randomPlace)) {
                parking.cars[randomPlace] = car
                parkingCounter ++
                println("Авто - ${car.name}, Номер - ${car.number}, Цвет - ${car.colorName}, Владелец - ${car.owner.name} запаркован на место P-${parking.getCarPlace(car)}")
                printAllParkingInfo()
                break
            }
        }
    }

    override fun returnCar(ownerName: String): Boolean {
        val car: Car? = parking.cars.values.find { it?.owner?.name == ownerName }
        return if (car != null) {
            parking.cars[parking.getCarPlace(car)] = null
            println("Авто - ${car.name}, Номер - ${car.number}, Цвет - ${car.colorName}, Владелец - ${car.owner.name}, возвращен владельцу")
            printAllParkingInfo()
            true
        } else {
            println("Автомобиль по владельцу не найден, введи другое имя владельца")
            false
        }
    }

    override fun getInfoByCar(carNumber: String): Boolean {
        val car = parking.cars.values.find { it?.number == carNumber }
        return if (car != null) {
            println("Авто с номером $carNumber припаркован на место P-${parking.getCarPlace(car)}")
            true
        } else {
            false
        }
    }

    override fun getInfoByPlace(place: Int): Boolean {
        val car = parking.cars[place]
        return if (car != null) {
            println("Авто - ${car.name}, Номер - ${car.number}, Цвет - ${car.colorName}, Владелец - ${car.owner.name} запаркован на место P-${place}")
            true
        } else {
            false
        }
    }

    override fun getCurrentStats() {
        println("--- --- --- ---")
        println("Сейчас свободно ${parking.maxParkingSize - parking.amountCarsOnParking} из ${parking.maxParkingSize} мест")
    }

    override fun getAllStats() {
        println("За всё время работы было запарковано $parkingCounter автомобилей")
    }

    private fun printAllParkingInfo() {
        if (parking.cars.values.filterNotNull().isNotEmpty()) {
            println("--- --- --- ---")
            println("Запаркованы следующие машины:")
            parking.cars.toSortedMap().forEach {
                if (it.value != null) { println("Место P-${it.key}, Авто - ${it.value?.name}, Номер - ${it.value?.number}, Цвет - ${it.value?.colorName}, Владелец - ${it.value?.owner?.name}") }
            }
        } else {
            getCurrentStats()
        }
    }
}