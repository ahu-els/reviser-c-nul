package fr.huzor

import tornadofx.*
import java.time.LocalDate
import java.time.Period

/**
 * Created by huzor on 19/04/2017.
 */
class Person(id: Int, name: String, birthday: LocalDate) {
    var id by property(id)
    fun idProperty() = getProperty(Person::id)

    var name by property(name)
    fun nameProperty() = getProperty(Person::name)

    var birthday by property(birthday)
    fun birthdayProperty() = getProperty(Person::birthday)

    val age: Int get() = Period.between(birthday, LocalDate.now()).years
}

/*
class MyView : View() {

    private val persons = listOf(
            Person(1, "Samantha Stuart", LocalDate.of(1981,12,4)),
            Person(2, "Tom Marks", LocalDate.of(2001,1,23)),
            Person(3, "Stuart Gills", LocalDate.of(1989,5,23)),
            Person(3, "Nicole Williams", LocalDate.of(1998,8,11))
    ).observable()

    override val root = tableview(persons) {
        column("ID", Person::idProperty)
        column("Name", Person::nameProperty)
        column("Birthday", Person::birthdayProperty)
        column("Age", Person::ageProperty)
    }
}*/
