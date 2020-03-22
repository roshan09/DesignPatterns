package decorator

fun main(args: Array<String>) {
    val widget = ColorDecorator(BorderLineDecorator(TextField()))
    widget.draw()
}


interface Widget {
    fun draw()
}

class TextField : Widget {
    override fun draw() {
        println("Drawing widget...")
    }
}

abstract class Decorator : Widget {
    abstract val widget : Widget
    abstract override fun draw()
}

class BorderLineDecorator(override val widget: Widget) : Decorator() {
    override fun draw() {
        widget.draw()
        println("Adding border lines...")
    }
}

class ColorDecorator(override val widget: Widget) : Decorator() {
    override fun draw() {
        widget.draw()
        println("Adding color...")
    }
}