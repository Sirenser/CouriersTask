import kotlin.random.Random

class OrderAgent {

    var fromLocation: Location? = null

    var toLocation: Location? = null

    var weight = 0.0

    var id = Random.nextInt(0, 100)

    val orderDistance: Double
        get() = Location.getDistance(fromLocation!!,toLocation!!) ?: 0.0

    fun orderPrice() = getOrderPrice()

    var currentPlan: PlanningOption? = null
        private set

    val isPlanned: Boolean
        get() = currentPlan != null


    private fun getOrderPrice(): Double {
        return orderDistance * CompanyAgent.PricePerDistance
    }

    fun getInfo(): String {
        return "Заказ № $id: ${fromLocation.toString()} -> ${toLocation.toString()}" +
                " (${orderDistance} км / ${weight} кг) | Цена: ${orderPrice()}"
    }

    fun planOrderAction(): Boolean {
        val couriers = findCouriers()
        val planningOptions = mutableListOf<PlanningOption>()
        couriers.forEach {
            val planningOption = it.requestPlanningOptionAction(this)
            planningOptions.add(planningOption)
        }
        if (planningOptions.isNotEmpty()) {
            val bestOption = getBestOption(planningOptions)
            if (bestOption != null) {
                bestOption.courier?.acceptPlanAction(bestOption)
                currentPlan = bestOption
                return true
            }
        }
        return false
    }

    private fun findCouriers(): List<CourierAgent> {
        return CompanyAgent.companyInstance.getAvailableCouriers()
            .filter { it.canCarry(this) }
    }

    private fun getBestOption(options: List<PlanningOption>): PlanningOption? {
        val sortedOption = options.sortedByDescending { it.profit }
        return sortedOption.firstOrNull { it.profit > 0 }
    }

}
