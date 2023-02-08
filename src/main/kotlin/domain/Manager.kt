package domain

import model.Car
import model.Parking

interface Manager {

    val parking: Parking

    val canParkCar: Boolean

    fun parkCar(car: Car)

    fun returnCar(ownerName: String): Boolean

    fun getInfoByCar(carNumber: String): Boolean

    fun getInfoByPlace(place: Int): Boolean
}