class PlanningOption {

    var order: OrderAgent? = null

    var courier: CourierAgent? = null

    var price: Double = 0.0

    val profit: Double
        get() = order?.orderPrice()?.minus(price) ?: 0.0
}