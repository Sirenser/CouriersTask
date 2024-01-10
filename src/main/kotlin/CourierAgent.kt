import java.util.*

abstract class CourierAgent {
    var name: String = ""
    var initialLocation: Location? = null
    var carryingCapacity: Double = 0.0
    var speed: Double = 0.0
    var courierPrice: Double = 0.0

    fun canCarry(order: OrderAgent): Boolean {
        return carryingCapacity >= order.weight
    }

    fun getInfo(): String {
        return "Курьер: $name|" +
                " Скорость: $speed |" +
                " Грузоподъмность $carryingCapacity |" +
                " Находится в ${initialLocation.toString()}"
    }

    internal fun acceptPlanAction(planningOption: PlanningOption) {
        scheduledOrder.addLast(planningOption.order)
    }

    internal fun requestPlanningOptionAction(order: OrderAgent): PlanningOption {
        val planningOption = PlanningOption()
        val currentCourierLocation = scheduledOrder.lastOrNull()?.toLocation ?: initialLocation
        val distance = Location.getDistance(currentCourierLocation!!,order.fromLocation!!) ?: 0.0 + order.orderDistance
        val courierCost = distance * courierPrice
        planningOption.courier = this
        planningOption.order = order
        planningOption.price = courierCost
        return planningOption
    }

    private val scheduledOrder = LinkedList<OrderAgent>()
}

class FootCourierAgent : CourierAgent() {
    init {
        speed = CompanyAgent.DefaultFootCourierSpeed
        courierPrice = CompanyAgent.PricePerDistance * 0.25
    }
}

class MobileCourierAgent : CourierAgent() {
    init {
        speed = CompanyAgent.DefaultMobileCourierSpeed
        courierPrice = CompanyAgent.PricePerDistance * 0.35
    }
}


