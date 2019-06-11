package hu.ait.timeshowdemo

class Car constructor(type: String) {

    var typeUpper = type.toUpperCase()
    var maxSpeed = 0
    var weight = 0

    constructor(type: String, maxSpeed: Int, weight: Int) : this(type) {
        this.maxSpeed = maxSpeed
        this.weight = weight
    }

    fun typeUpper() : String{
        return typeUpper
    }

    fun getProperties() : String {
        return "Max speed: $maxSpeed, weight: $weight"
    }

}