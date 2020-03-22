package behavioral.observer

fun main(args: Array<String>) {
    val channel = Channel("channel1")
    val channel2 = Channel("channel2")

    val user1 = User("user1")
    val user2 = User("user2")
    val user3 = User("user3")
    val user4 = User("user4")

    user1.subscribeTo(channel)
    user2.subscribeTo(channel)
    user3.subscribeTo(channel)

    user4.subscribeTo(channel2)

    channel.update()
    channel2.update()
}


abstract class Publisher {
    private val subscribers = mutableListOf<Subscriber>()
    fun subscribe(subscriber: Subscriber) {
        subscribers.add(subscriber)
    }

    fun notifyAllSubscribers() {
        subscribers.forEach { it.notifyMe() }
    }
}


abstract class Subscriber {
    abstract fun notifyMe();

    fun subscribeTo(publisher: Publisher) {
        publisher.subscribe(this)
    }
}


class Channel(val channelId : String) : Publisher() {

    fun update() {
        println("Updating channel : $channelId")
        notifyAllSubscribers()
    }
}


class User(private val userId: String) : Subscriber() {
    override fun notifyMe() {
        println("Notification received to $userId")
    }
}