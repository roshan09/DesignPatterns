package composite

/*
    Use this pattern when you have hierarchical relation and you want to perform an operation on each of them.
    In this  example we need to perform a common action of traversing and printing the name of each Dir/File.
 */


fun main(args: Array<String>) {
    val etcDir = Directory("etc", listOf(File("etcFile1"), File("etcFile2"), Directory("etcSubDir", listOf(File("etcSubDire_File1")))))
    val binDir = Directory("bin", listOf(File(".g++"), File("ls"), File("ps")))
    val fileSystem = Directory("/", listOf(etcDir, binDir))

    fileSystem.traverse()
}

interface Entity {
    fun traverse()
}

class Directory(private val name: String, private val subEntities: List<Entity>) : Entity {
    override fun traverse() {
        println("name : $name is a directory\ntraversing into the directory")
        subEntities.forEach { it.traverse() }
    }
}


class File(private val name: String) : Entity {
    override fun traverse() {
        println("name : $name is a file")
    }
}