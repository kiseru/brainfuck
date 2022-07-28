package com.kiseru.brainfuck

import java.io.BufferedReader
import java.io.FileReader

/**
 * Размерность ленты.
 */
private const val TAPE_LENGTH = 30000;

fun main(args: Array<String>) {
    val filename = args[0]
    val brainfuck = Brainfuck(CharArray(TAPE_LENGTH), BufferedReader(FileReader(filename)))
    brainfuck.run()
}
