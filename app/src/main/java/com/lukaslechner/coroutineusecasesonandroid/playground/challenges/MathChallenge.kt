package com.lukaslechner.coroutineusecasesonandroid.playground.challenges

// Challenge
// Have the function MathChallenge(str) take the str parameter, which will be a simple mathematical formula with three numbers, a single operator (+, -, *, or /) and an equal sign (=) and return the digit that completes the equation. In one of the numbers in the equation, there will be an x character, and your program should determine what digit is missing. For example, if str is "3x + 12 = 46" then your program should output 4. The x character can appear in any of the three numbers and all three numbers will be greater than or equal to 0 and less than or equal to 1000000.
// Examples
// Input: "4 - 2 = x"
// Output: 2
// Input: "1x0 * 12 = 1200"
// Output: 0
// Input: "3x + 12 = 46"
// Output: 4
//Constrainst
//1. The x character can appear in any of the three numbers -> has x in it
//2. all three numbers will be greater than or equal to 0 and less than or equal to 1000000.

//Identify the missing number
private fun MathChallenge(str: String): String {
    //validate constraints 1 -> has x in it
    if (!str.contains("x")) return "not possible"

    //validate constraints 2 -> all three numbers will be greater than or equal to 0 and less than or equal to 1000000.
    val numbersSplit = str.split(" ")
    //remove operators and equal sign
    val numbersOnlyValid = numbersSplit.filter { it != "+" && it != "-" && it != "*" && it != "/" && it != "=" }
    //convert to int
    val numbersOnlyIntegers = numbersOnlyValid.map {
        //if it contains x, replace it with 1
        if (it.contains("x")) 1 else it.toInt()
    }
    //check if all numbers are between 0 and 1000000
    numbersOnlyIntegers.forEach {
        if (it < 0 || it > 1000000) return "not possible"
    }

    for(digit in 0..9){
        val newStr = str.replace("x", digit.toString())
        val numbers = newStr.split(" ")
        val numbersOnly = numbers.filter { it != "+" && it != "-" && it != "*" && it != "/" && it != "=" }
        val numbersOnlyInt = numbersOnly.map {
            //if it contains x, replace it with 1
            if (it.contains("x")) 1 else it.toInt()
        }
        val firstNumber = numbersOnlyInt[0]
        val secondNumber = numbersOnlyInt[1]
        val thirdNumber = numbersOnlyInt[2]
        val operator = numbers[1]

        //check if the equation is correct
        if (operator == "+") {
            if (firstNumber + secondNumber == thirdNumber) return digit.toString()
        }
        if (operator == "-") {
            if (firstNumber - secondNumber == thirdNumber) return digit.toString()
        }
        if (operator == "*") {
            if (firstNumber * secondNumber == thirdNumber) return digit.toString()
        }
        if (operator == "/") {
            if (firstNumber / secondNumber == thirdNumber) return digit.toString()
        }
    }

    //if no digit is found
    return "not possible"
}

fun main () {
    println(MathChallenge("4 - 2 = x"))
    println(MathChallenge("1x0 * 12 = 1200"))
    println(MathChallenge("3x + 12 = 46"))
}