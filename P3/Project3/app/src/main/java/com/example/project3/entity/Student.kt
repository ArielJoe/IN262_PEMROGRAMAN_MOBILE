package com.example.project3.entity

class Student(val studentId: String, firstName: Int, lastName: String) : Person(firstName, lastName) {

    override fun showInfo(): String {
        if (lastName == null) {
            return  "$studentId $firstName"
        }
        return  "$studentId $firstName $lastName"
    }
}