package com.quantum.soft.ericsson.lte

import ru.cwt.asn1.ericsson.sgw_r9.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/*
        if input == "*":
            return "1010"
        elif input == "#":
            return "1011"
        elif input == "a":
            return "1100"
        elif input == "b":
            return "1101"
        elif input == "c":
            return "1100"
        else:
            print("input " + str(input) + " is not a special char, converting to bin ")
            return ("{:04b}".format(int(input)))
 */

val specialCharacters = mapOf(
    '*' to "1010",
    '#' to "1011",
    'a' to "1100",
    'b' to "1101",
    'c' to "1100"
)


fun String.tbcdEncode(): String = removeTrailing(encode(this))


fun encode(s: String): String {

    val stringBuffer = StringBuffer()
    for(index in s.indices step 2 ){
        val substring = s
            .substring(index, index + 2)
            .reversed()

        substring.forEach { ch ->
                val charValue = specialCharacters[ch] ?: ch
                stringBuffer.append(charValue)
            }
    }

    return stringBuffer.toString()
}


fun removeTrailing(s: String) = if(s.toUpperCase().endsWith('F')) s.substring(0, s.length - 2) else s
