package com.example.brainmath.Questions // Package declaration

import java.io.Serializable

// Data class representing a question in the app
data class Question(
    val problem: String, // Holds the mathematical problem as a string
    val answer: String, // Holds the correct answer to the problem
    val option1: String, // Holds the first option for the problem
    val option2: String, // Holds the second option for the problem
    val option3: String, // Holds the third option for the problem
    val option4: String, // Holds the fourth option for the problem
    var selectedOption: String // Holds the option selected by the user, initially empty
): Serializable // Implements Serializable to allow objects of this class to be serialized
