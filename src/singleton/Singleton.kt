package singleton

fun main(args: Array<String>) {
    println(Manager.name)
}


object Manager{

    val name = "Manager_123"
}