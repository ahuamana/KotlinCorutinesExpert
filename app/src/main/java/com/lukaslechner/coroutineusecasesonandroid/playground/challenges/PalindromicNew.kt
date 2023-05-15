package com.lukaslechner.coroutineusecasesonandroid.playground.challenges

//Have the function StringChallenge(str) take the str parameter being passed and determine if it is possible to create a palindromic string of minimum length 3 characters by removing 1 or 2 characters
//For example: if str is "abjchba" then you can remove the characters jc to produce "abhba" which is a palindrome.
//For this example your program should return the two characters that were removed with no delimiter and in the order they appear in the string, so jc.
//If 1 or 2 characters cannot be removed to produce a palindrome, then return the string not possible.
//If the input string is already a palindrome, your program should return the string palindrome.
//The input will only contain lowercase alphabetic characters.
//Your program should always attempt to create the longest palindromic substring by removing 1 or 2 characters
// (see second sample test case as an example).
//The 2 characters you remove do not have to be adjacent in the string.
//Examples
//Input: "mmop"
//Output: not possible
//Input: "kjjjhjjj"
//Output: k

private fun stringChallenge(str: String): String {
    if (str == str.reversed()) return "palindrome"
    //min length 3 characters
    if (str.length < 3) return "not possible"

    str.indices.forEachIndexed { index, _ ->
        //remove 1 character
        val newStr = str.removeRange(index, index + 1)
        //min length 3 characters
        if (newStr.length < 3) return "not possible"
        if (newStr == newStr.reversed()) return str[index].toString()
        //remove 2 characters
    }

    //remove 2 characters (not adjacent)
    str.indices.forEachIndexed { index, _ ->
        val newStr = str.removeRange(index, index + 2)
        if (newStr.length < 3) return "not possible"
        if (newStr == newStr.reversed()) return str[index].toString() + str[index + 1].toString()
    }

    //If any of the above conditions are not met, return "not possible"
    return "not possible"
}

//test StringChallenge

fun main () {
    println(stringChallenge("abh"))
    println(stringChallenge("abhba"))
    println(stringChallenge("mmop"))
    println(stringChallenge("kjjjhjjj"))
    println(stringChallenge("abjchba"))
}