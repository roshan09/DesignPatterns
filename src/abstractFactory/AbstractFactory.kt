package abstractFactory

import abstractFactory.WheelType.*
import abstractFactory.DoorType.*

fun main(args: Array<String>) {
    val wheelFactory = AbstractFactory.create(FactoryType.WHEEL)
    val wheelTypeA = wheelFactory.create(WHEEL_TYPE_A.toString())
    println(wheelTypeA.message)

    val doorFactory = AbstractFactory.create(FactoryType.DOOR)
    val doorTypeA = doorFactory.create(DOOR_TYPE_A.toString())
    println(doorTypeA.message)

}

abstract class AbstractFactory {
    companion object {
        fun create(factoryType: FactoryType): Factory<out Product> {
            return when (factoryType) {
                FactoryType.WHEEL -> WheelFactory()
                FactoryType.DOOR -> DoorFactory()
            }
        }
    }
}

abstract class Factory<T : Product> {
    abstract fun create(type: String): T
}

class WheelFactory : Factory<Wheel>() {
    override fun create(type: String): Wheel {
        val wheelType = WheelType.valueOf(type)
        return when (wheelType) {
            WHEEL_TYPE_A -> WheelTypeA()
            WHEEL_TYPE_B -> WheelTypeB()
            WHEEL_TYPE_C -> WheelTypeC()
        }
    }
}

class DoorFactory : Factory<Door>() {
    override fun create(type: String): Door {
        val doorType = DoorType.valueOf(type)
        return when (doorType) {
            DOOR_TYPE_A -> DoorTypeA()
            DOOR_TYPE_B -> DoorTypeB()
            DOOR_TYPE_C -> DoorTypeC()
        }
    }
}

abstract class Product(val message: String)

abstract class Wheel(message: String) : Product(message)

class WheelTypeA : Wheel("This is WheelTypeA")

class WheelTypeB : Wheel("This is WheelTypeB")

class WheelTypeC : Wheel("This is WheelTypeC")

abstract class Door(message: String) : Product(message)

class DoorTypeA : Door("This is DoorTypeA")

class DoorTypeB : Door("This is DoorTypeB")

class DoorTypeC : Door("This is DoorTypeC")

enum class WheelType {
    WHEEL_TYPE_A,
    WHEEL_TYPE_B,
    WHEEL_TYPE_C
}


enum class DoorType {
    DOOR_TYPE_A,
    DOOR_TYPE_B,
    DOOR_TYPE_C
}

enum class FactoryType {
    WHEEL,
    DOOR
}