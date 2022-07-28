package com.kiseru.brainfuck

import java.nio.file.Files
import java.nio.file.Paths

/**
 * Размерность ленты.
 */
private const val TAPE_LENGTH = 30000

fun main(args: Array<String>) {
    val filename = Paths.get(args[0])
    val bufferedReader = Files.newBufferedReader(filename)
    val brainfuck = Brainfuck(CharArray(TAPE_LENGTH), bufferedReader)
    brainfuck.run()
}
