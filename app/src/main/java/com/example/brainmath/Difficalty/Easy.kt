package com.example.brainmath.Difficalty // Package declaration

import java.lang.ArithmeticException
import kotlin.random.Random

object Easy { // Singleton object named Easy

    private var answer:Int = 0 // Variable to store the answer

    // Function to generate a question and its answer
    fun getQuestions():Pair<String, Int>{

        // Generate two random numbers between -100 and 100
        val num1 = Random.nextInt(-100,100)
        val num2 = Random.nextInt(-100,100)

        // Randomly select an arithmetic operator
        val operator = arrayOf("+", "-", "x", "/").random()

        // Combine the numbers and operator to form the problem string
        val problem = "$num1 $operator $num2"

        // Calculate the answer based on the operator
        try {
            answer = when(operator){ // Handle division by zero
                "+" -> num1 + num2
                "-" -> num1 - num2
                "x" -> num1 * num2
                else -> num1 / num2
            }
        }catch (e:ArithmeticException){
            answer = 77777777 // Assign a special value as answer if division by zero occurs
        }

        // Return the problem string and the answer as a Pair
        return Pair(problem, answer)
    }
}