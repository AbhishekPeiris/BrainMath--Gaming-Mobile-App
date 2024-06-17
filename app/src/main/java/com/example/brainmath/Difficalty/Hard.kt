package com.example.brainmath.Difficulty // Package declaration

import java.lang.ArithmeticException
import kotlin.random.Random

object Hard { // Singleton object named Hard

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
        // Generate four random numbers between 1 and 100
        val num1 = Random.nextInt(1, 100)
        val num2 = Random.nextInt(1, 100)
        val num3 = Random.nextInt(1, 100)
        val num4 = Random.nextInt(1, 100)

        // Randomly determine the position of brackets
        val bracketPosition = Random.nextInt(1, 4)

        // Randomly select arithmetic operators for three operations
        val operator1 = arrayOf("+", "-", "x", "/").random()
        val operator2 = arrayOf("+", "-", "x", "/").random()
        val operator3 = arrayOf("+", "-", "x", "/").random()

        // Create the problem string based on bracketPosition
        val problem = when (bracketPosition) {
            1 -> "(($num1 $operator1 $num2) $operator2 $num3) $operator3 $num4"
            2 -> "($num1 $operator1 ($num2 $operator2 $num3)) $operator3 $num4"
            else -> "($num1 $operator1 $num2) $operator2 ($num3 $operator3 $num4)"
        }

        // Calculate the first intermediate result based on bracketPosition
        val halfAns1 = when (bracketPosition) {
            1 -> makeOperation(num1, num2, operator1)
            2 -> makeOperation(num2, num3, operator2)
            else -> makeOperation(num1, num2, operator1)
        }

        // Calculate the second intermediate result based on bracketPosition
        val halfAns2 = when (bracketPosition) {
            1 -> makeOperation(halfAns1, num3, operator2)
            2 -> makeOperation(num1, halfAns1, operator1)
            else -> makeOperation(num3, num4, operator3)
        }

        // Calculate the final answer based on bracketPosition and intermediate results
        var answer = when (bracketPosition) {
            3 -> makeOperation(halfAns1, halfAns2, operator2)
            else -> makeOperation(halfAns2, num4, operator3)
        }

        // Check if there was a division by zero in the second intermediate result calculation
        if (halfAns2 == 77777777) {
            answer = 77777777 // Set answer to special value if division by zero occurred
        }

        // Return the problem string and the answer as a Pair
        return Pair(problem, answer)
    }
}
