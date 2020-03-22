package adapter

fun main(args: Array<String>) {
    val client = Device(Ethernet())
    client.switchOn()

    val usbAdapter = UsbAdapter(Usb())
    val updatedClient = Device(usbAdapter)
    updatedClient.switchOn()
}


class Device(private val ethernet: IEthernet) {

    fun  switchOn() {
        ethernet.connectToEthernet()
    }
}

interface IEthernet {
    fun connectToEthernet()
}

open class Ethernet : IEthernet {
    override fun connectToEthernet() {
        println("Connecting to Ethernet... ")
    }
}


interface IUsb{
    fun connectToUsb()
}

class Usb : IUsb {
    override fun connectToUsb() {
        println("Connecting to USB... ")
    }
}

class UsbAdapter(private val adaptee: IUsb) : IEthernet {
    override fun connectToEthernet() {
        println("Converting ethernet connection to usb...")
        adaptee.connectToUsb()
    }
}