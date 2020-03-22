package objectPool

/*
    New employee gets laptops on the first day. Techops people checks weather they have any laptop.
    If yes then they will give it to new Employee. otherwise the will order a new one and give it to
    new employee. if they don't have budget to buy new laptop they will ask the employee to wait for 2-3 days

    Example :
        1) Database connections pool

    When to use this :

        1) Creating a connection is an expensive operation.
        2) When there are too many connections opened it takes longer to create a new one and the database server will become overloaded.

 */



fun main(args: Array<String>) {
    val employee1 = Employee("TWE1")
    val employee2 = Employee("TWE2")
    val employee3 = Employee("TWE3")

    val management = EmployeeManagement()
    management.provideLaptopToEmployee(employee1)
    management.provideLaptopToEmployee(employee2)
    management.provideLaptopToEmployee(employee3)

    management.exitEmployee(employee2)

    val employee4 = Employee("TWE4")
    management.provideLaptopToEmployee(employee4)

    employee1.displayDetails()
    employee2.displayDetails()
    employee3.displayDetails()
    employee4.displayDetails()
}

class EmployeeManagement {
    private val techOps = TechOps()

    fun provideLaptopToEmployee(employee: Employee) {
        require(employee.laptop == null)
        val laptop = techOps.findAndProvideLaptop()
        employee.assignLaptop(laptop)
    }

    fun exitEmployee(employee: Employee) {
        val laptop = employee.laptop
        require(laptop != null)
        techOps.addLaptopToWareHouse(laptop)
    }
}

data class Laptop(val serialNumber: Int)

object WareHouse {
    private val spareLaptops = mutableListOf<Laptop>()
    private val allLaptops = mutableSetOf<Laptop>()

    fun addSpareLaptop(laptop: Laptop) {
        spareLaptops.add(laptop)
    }

    fun addLaptopEntry(laptop: Laptop) {
        allLaptops.add(laptop)
    }

    fun numberOfSpareLaptops(): Int {
        return spareLaptops.size
    }

    fun numberOfAllLaptops(): Int {
        return allLaptops.size
    }

    fun getALaptop(): Laptop {
        return spareLaptops.removeAt(0)
    }
}

object Amazon {

    var currSerialNumber = 0

    fun orderLaptop(): Laptop {
        currSerialNumber++
        return Laptop(currSerialNumber)
    }
}

class TechOps {

    private val LIMIT = 3

    fun findAndProvideLaptop(): Laptop {
        val numberOfSpareLaptops = WareHouse.numberOfSpareLaptops()
        if (numberOfSpareLaptops != 0) {
            return WareHouse.getALaptop()
        } else {
            val numberOfAllLaptops = WareHouse.numberOfAllLaptops()
            if (numberOfAllLaptops < LIMIT) {
                val laptop = Amazon.orderLaptop()
                WareHouse.addLaptopEntry(laptop)
                return laptop
            } else {
                // Need to handle
                return Laptop(-1)
            }
        }

    }

    fun addLaptopToWareHouse(laptop: Laptop) {
        WareHouse.addSpareLaptop(laptop)
    }
}

class Employee(val employeeId: String) {
    var laptop: Laptop? = null

    fun assignLaptop(laptop: Laptop) {
        this.laptop = laptop
    }

    fun displayDetails() {
        println("employeeId : $employeeId , laptop : $laptop")
    }
}