package builder

// https://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/

fun main(args: Array<String>) {
    val user1 = UserBuilder
        .initiate("Roshan", "Bhor")
        .addDob("12/12/12")
        .addGender(GENDER.MALE)
        .build()

    val user2 = UserBuilder
        .initiate("Ashish", "Bhor")
        .addDob("11/11/11")
        .addGender(GENDER.MALE)
        .build()

    user1.displayDetails()
    user2.displayDetails()
}


object UserBuilder {

    private var fname: String = ""
    private var lname: String = ""
    private var dob: String = ""
    private var gender: GENDER = GENDER.MALE

    fun initiate(fname: String, lname: String): UserBuilder {
        this.fname = fname
        this.lname = lname
        return this
    }

    fun addDob(dob: String): UserBuilder {
        this.dob = dob
        return this
    }

    fun addGender(gender: GENDER): UserBuilder {
        this.gender = gender
        return this
    }

    fun build(): User {
        return User(this)
    }

    class User {
        private val fname: String
        private val lname: String
        private val dob: String
        private val gender: GENDER

        constructor(userBuilder: UserBuilder) {
            this.fname = userBuilder.fname
            this.lname = userBuilder.lname
            this.dob = userBuilder.dob
            this.gender = userBuilder.gender
        }

        fun displayDetails() {
            println("First Name -> " + this.fname + "\nLast Name  -> " + this.lname + "\nDate Of Birth -> " + this.dob + "\nGender -> " + this.gender)
        }
    }
}

enum class GENDER {
    MALE,
    FEMALE
}


