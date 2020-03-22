package behavioral.chainOfResponsibility

fun main(args: Array<String>) {
    val filterChainStart = FirstFilter()

    filterChainStart
        .setNext(SecondFilter())
        .setNext(ThirdFilter())

    val request1  = mapOf("first" to true, "second" to true, "third" to true)
    val result1 = filterChainStart.apply(request1)
    println("result1  $result1")

    val request2  = mapOf("first" to true, "second" to false, "third" to true)
    val result2 = filterChainStart.apply(request2)
    println("result2  $result2")


}


abstract class AbstractFilter {

    abstract fun doFilter(request: Map<String, Boolean>): Boolean

    private var next: AbstractFilter? = null

    fun apply(request: Map<String, Boolean>): Boolean {
        val result = doFilter(request)
        return result && (next?.apply(request) ?: true)
    }

    open fun setNext(next: AbstractFilter): AbstractFilter {
        this.next = next
        return next
    }
}

class FirstFilter : AbstractFilter() {
    override fun doFilter(request: Map<String, Boolean>): Boolean {
        println("Applying first filter")
        return request["first"] ?: false
    }
}


class SecondFilter : AbstractFilter() {
    override fun doFilter(request: Map<String, Boolean>): Boolean {
        println("Applying second filter")
        return request["second"] ?: false
    }
}

class ThirdFilter : AbstractFilter() {
    override fun doFilter(request: Map<String, Boolean>): Boolean {
        println("Applying third filter")
        return request["third"] ?: false
    }
}