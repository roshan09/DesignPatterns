package composite

fun main(args: Array<String>) {
    val rightExpression = Composite(Composite(Primitive(1), '+', Primitive(2)), '*', Primitive(6))
    val expression = Composite(Primitive(5), '+', rightExpression)
    expression.traverse()
}


interface Component {
    fun traverse()
}


class Primitive(private val value: Int) : Component {
    override fun traverse() {
        print(value)
    }
}

class Composite(private val left: Component, private val operator: Char, private val right: Component) : Component {
    override fun traverse() {
        print("( ")
        left.traverse()
        print(" $operator ")
        right.traverse()
        print(" )")
    }
}