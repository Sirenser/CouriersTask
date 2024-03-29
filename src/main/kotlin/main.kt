var isResumed = true

fun main() {

    val currier1 = FootCourierAgent().apply {
        name = "Вася"
        initialLocation = Location(5, 5)
        carryingCapacity = 5.0
    }
    val currier2 = FootCourierAgent().apply {
        name = "Петя"
        initialLocation = Location(3, 3)
        carryingCapacity = 6.0
    }
    val currier3 = MobileCourierAgent().apply {
        name = "Коля"
        initialLocation = Location(1, 1)
        carryingCapacity = 8.0
    }
    val currier4 = MobileCourierAgent().apply {
        name = "Нина"
        initialLocation = Location(3, 3)
        carryingCapacity = 2.0
    }
    val order1 = OrderAgent().apply {
        fromLocation = Location(3, 3)
        toLocation = Location(2, 2)
        weight = 5.0
    }
    val order2 = OrderAgent().apply {
        fromLocation = Location(5, 5)
        toLocation = Location(5, 1)
        weight = 4.0
    }
    val order3 = OrderAgent().apply {
        fromLocation = Location(1, 5)
        toLocation = Location(1, 1)
        weight = 3.0
    }
    val order4 = OrderAgent().apply {
        fromLocation = Location(2, 2)
        toLocation = Location(3, 3)
        weight = 3.0
    }
    val order5 = OrderAgent().apply {
        fromLocation = Location(1, 5)
        toLocation = Location(5, 1)
        weight = 2.0
    }
    val company = CompanyAgent.companyInstance
    company.couriers.add(currier1)
    company.couriers.add(currier2)
    company.couriers.add(currier3)
    company.couriers.add(currier4)
    company.orders.add(order1)
    company.orders.add(order2)
    company.orders.add(order3)
    company.orders.add(order4)
    company.orders.add(order5)
    company.printOrders()
    println()
    company.printCouriers()
    company.startPlaner()
    println("-----------------------------------------------------------------")

    while (isResumed) {
        println("Введите число для выбора опции:\n" +
                " 1. Добавить пешего курьера;\n " +
                " 2. Добавить мобильного курьера;\n" +
                " 3. Удалить курьера;\n" +
                " 4. Добавить заказ;\n" +
                " 5. Удалить заказ;\n " +
                " 6. Закончить ввод;\n" +
                " 7. Закончить программу;\n")
        var isCycled = true
        while (isCycled) {
            when (readLine()) {
                "1" -> {
                    company.couriers.add(
                        TaskUtils.addFootCourier()
                    )
                    println("Добавлен пеший курьер")
                }
                "2" -> {
                    company.couriers.add(
                        TaskUtils.addMobileCourier()
                    )
                    println("Добавлен мобильный курьер")
                }
                "3" -> {
                    company.couriers.removeIf {
                        it.name == TaskUtils.removeCourier()
                    }
                    println("Курьер удален")
                }
                "4" -> {
                    company.orders.add(
                        TaskUtils.addOrder()
                    )
                    println("Заказ добавлен")
                }
                "5" -> {
                    company.orders.removeIf {
                        it.id == TaskUtils.removeOrderById()
                    }
                    println("Заказ удален")
                }
                "6" -> {
                    println("Ввод окончен")
                    isCycled = false
                }
                "7" -> {
                    println("Программа завершена")
                    isResumed = false
                    return
                }
                else -> {
                    println("Введите корректное значение")
                    continue
                }
            }
        }
        company.printOrders()
        println("-----------------------------------------------------------------")
        company.printCouriers()
        println("-----------------------------------------------------------------")
        company.startPlaner()
    }

}

object TaskUtils {

    fun addFootCourier(): FootCourierAgent {
        print("Введите имя курьера(Name): ")
        val _name = readLine()
        print("Введите начальную локацию(x,y): ")
        val _location = readLine()
        val _xCord = _location?.get(0)?.toInt() ?: 10
        val _yCord = _location?.get(2)?.toInt() ?: 10
        print("Введите переносимый вес(0.0): ")
        val _carryingCapacity = readLine()
        return FootCourierAgent().apply {
            name = _name ?: "Не указано"
            initialLocation = Location(xCoord = _xCord, yCoord = _yCord)
            carryingCapacity = _carryingCapacity?.toDouble() ?: 5.0
        }
    }

    fun addMobileCourier(): MobileCourierAgent {
        print("Введите имя курьера(Name): ")
        val _name = readLine()
        print("Введите начальную локацию(x,y): ")
        val _location = readLine()
        val _xCord = _location?.get(0)?.toInt() ?: 10
        val _yCord = _location?.get(2)?.toInt() ?: 10
        print("Введите переносимый вес(0.0): ")
        val _carryingCapacity = readLine()
        return MobileCourierAgent().apply {
            name = _name ?: "Не указано"
            initialLocation = Location(xCoord = _xCord, yCoord = _yCord)
            carryingCapacity = _carryingCapacity?.toDouble() ?: 5.0
        }
    }

    fun addOrder(): OrderAgent {
        print("Введите начальную локацию(x,y): ")
        val _location1 = readLine()
        val _xCord1 = _location1?.get(0)?.toInt() ?: 10
        val _yCord1 = _location1?.get(2)?.toInt() ?: 10
        print("Введите конечную локацию(x,y): ")
        val _location2 = readLine()
        val _xCord2 = _location2?.get(0)?.toInt() ?: 10
        val _yCord2 = _location2?.get(2)?.toInt() ?: 10
        print("Введите вес(0.0): ")
        val _carryingCapacity = readLine()
        return OrderAgent().apply {
            fromLocation = Location(_xCord1, _yCord1)
            toLocation = Location(_xCord2, _yCord2)
            weight = _carryingCapacity?.toDouble() ?: 5.0
        }
    }

    fun removeCourier(): String {
        print("Введите имя удаляемого курьера(Name): ")
        return readLine() as String
    }

    fun removeOrderById(): Int {
        print("Введите номер заказа в списке: ")
        return readLine()?.get(0)?.toInt() ?: 1
    }

}