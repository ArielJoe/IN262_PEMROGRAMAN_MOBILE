package com.example.project3.entity

open class Person(var firstName: Int, var lastName: String) {

    open fun showInfo(): Any {
        if (lastName == null) {
            return  firstName
        }
        return "$firstName $lastName"

//        return lastName?.let { it -> "$firstName $it" } ?: firstName
    }
}