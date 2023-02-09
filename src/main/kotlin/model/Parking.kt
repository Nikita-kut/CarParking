package model

data class Parking(
    val maxParkingSize: Int,
    val cars: MutableMap<Int, Car?> = mutableMapOf(),
) {

    init {
        for (i in 1..maxParkingSize) {
            cars[i] = null
        }
    }

    val amountCarsOnParking: Int get() = cars.values.filterNotNull().size

    fun getCarPlace(car: Car?): Int = cars.filterValues { it == car }.keys.first()
}
