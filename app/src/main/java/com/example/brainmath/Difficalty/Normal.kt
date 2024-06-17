package com.example.brainmath.Difficulty // Package declaration

import java.lang.ArithmeticException
import kotlin.random.Random

object Normal { // Singleton object named Normal

    // Function to perform arithmetic operations and handle exceptions
    private fun makeOperation(num1: Int, num2: Int, op: String): Int {
        val ans: Int = try {
            when (op) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "x" -> num1 * num2
                else -> num1 / num2
            }
        } catch (e: ArithmeticException) {
            77777777 // Special value for division by zero case
        }
        return ans
    }

    // Function to generate a question and its answer
    fun getQuestions(): Pair<String, Int> {
        // Generate three random numbers between 1 and 100
        val num1 = Random.nextInt(1, 100)
        val num2 = Random.nextInt(1, 100)
        val num3 = Random.nextInt(1, 100)

        // Randomly determine the position of brackets
        val bracketPosition = Random.nextInt(1, 3)

        // Randomly select arithmetic operators for two operations
        val operator1 = arrayOf("+", "-", "x", "/").random()
        val operator2 = arrayOf("+", "-", "x", "/").random()

        // Create the problem string based on bracketPosition
        val problem = when (bracketPosition) {
            1 -> "($num1 $operator1 $num2) $operator2 $num3"
            else -> "$num1 $operator1 ($num2 $operator2 $num3)"
        }

        // Calculate the intermediate result based on bracketPosition
        val halfAns = when (bracketPosition) {
            1 -> makeOperation(num1, num2, operator1)
            else -> makeOperation(num2, num3, operator2)
        }

        // Calculate the final answer based on bracketPosition and intermediate result
        val answer = when (bracketPosition) {
            1 -> makeOperation(halfAns, num3, operator2)
            else -> makeOperation(num1, halfAns, operator1)
        }

        // Return the problem string and the answer as a Pair
        return Pair(problem, answer)
    }
}
