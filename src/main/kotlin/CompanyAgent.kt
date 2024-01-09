import java.util.*
import kotlin.math.roundToInt

internal class CompanyAgent {

    companion object {

        const val PricePerDistance = 150.0
        const val DefaultFootCourierSpeed = 4.0
        const val DefaultMobileCourierSpeed = 8.0

        private var instance: CompanyAgent? = null

        val companyInstance: CompanyAgent
            get() {
                if (instance == null) {
                    instance = CompanyAgent()
                }
                return instance!!
            }
    }


    val couriers: HashSet<CourierAgent> = HashSet()
    val ordersQueue: Queue<OrderAgent> = LinkedList()
    val orders: HashSet<OrderAgent> = HashSet()

    fun getAvailableCouriers(): HashSet<CourierAgent> {
        return couriers
    }

    fun printOrders() {
        for (order in orders) {
            println(order.getInfo())
        }
    }

    fun printCouriers() {
        couriers.forEach {
            println(it.getInfo())
        }
    }

    fun startPlaner() {
        prepareQueue()
        planningCycle()
    }

    private fun prepareQueue() {
        val sortedOrders = orders.sortedByDescending { it.orderPrice() }
        for (order in sortedOrders) {
            ordersQueue.add(order)
        }
    }

    private fun planningCycle() {
        var totalProfit = 0.0
        while (ordersQueue.isNotEmpty()) {
            val orderForPlanning = ordersQueue.remove()
            println("Планируется заказ: ${orderForPlanning.getInfo()}")
            println()
            val result = orderForPlanning.planOrderAction()
            if (result) {
                totalProfit += orderForPlanning.currentPlan!!.profit
                println("Заказ запланирован: ${orderForPlanning.currentPlan!!.courier!!.name} " +
                        "c прибылью: ${orderForPlanning.currentPlan!!.profit.roundToInt()}")
            } else {
                println("Заказ не ��апланирован")
            }
        }
        println("Итоговая прибыль: ${totalProfit.roundToInt()}")
    }

}