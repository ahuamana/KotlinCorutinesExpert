package com.lukaslechner.coroutineusecasesonandroid.playground.challenges

//find the longest substring that contains k unique characters
//The input string str must have at least length 2.
//The first character of str must be a digit between 1 and 6 (inclusive), representing the number of unique characters in the longest substring to be found.
//The remaining characters of str must be printable ASCII characters (i.e., characters with ASCII codes between 32 and 126, inclusive).
//The function should return the longest substring of str that contains exactly k unique characters, where k is the digit extracted from the first character of str.
//If there are multiple longest substrings that satisfy the above condition, the function should return the first one.
fun SearchingChallenge(str: String): String {
    //The input string str must have at least length 2.
    if(str.length < 2) return "not possible"

    //get the number of unique characters handle exception use elvis operator
    val k = str[0].toString().toIntOrNull() ?: return "not possible"

    if(k !in 1..6) return "not possible"

    //Remove the first character from the string
    val substring = str.substring(1)

    //get the longest substring that contains k unique characters
    val longestSubstring = getLongestSubstring(substring, k)
    return if(longestSubstring.isEmpty()) "not possible" else longestSubstring
}

fun getLongestSubstring(substring: String, k: Int): String {
    //get the list of all substrings
    val substrings = mutableListOf<String>()
    for (i in substring.indices) {
        for (j in i + 1..substring.length) {
            substrings.add(substring.substring(i, j))
        }
    }

    //get the list of all substring that contains k unique characters
    val substringsWithKUniqueChars = mutableListOf<String>()
    substrings.forEach {
        if(it.toSet().size == k) substringsWithKUniqueChars.add(it)
    }

    //get the longest substring that contains k unique characters avoid use maxByOrNull
    var longestSubstring = ""
    substringsWithKUniqueChars.forEach {
        if(it.length > longestSubstring.length) longestSubstring = it
    }

    return longestSubstring
}

fun main() {
    println(SearchingChallenge("3aabacbebebe")) //cbebebe
    println(SearchingChallenge("2aabbcbbbadef")) //bbcbbb
    println(SearchingChallenge("2aabbacbaa")) //aabba
}