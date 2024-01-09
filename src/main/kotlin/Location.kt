import kotlin.math.abs
import kotlin.math.pow
import kotlin.properties.Delegates.notNull

class Location() {

    var xCoord: Int by notNull()

    var yCoord: Int by notNull()

    constructor(xCoord: Int, yCoord: Int) : this() {
        this.xCoord = xCoord
        this.yCoord = yCoord
    }

    companion object {

        fun getDistance(from: Location, destination: Location): Double? {
            return abs(
                (destination.xCoord - from.xCoord).toDouble().pow(2) +
                        (destination.yCoord - from.yCoord).toDouble().pow(2)
            )
        }

    }
}