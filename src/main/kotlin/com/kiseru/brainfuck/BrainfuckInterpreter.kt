package com.kiseru.brainfuck

import java.nio.file.Files
import java.nio.file.Paths

/**
 * Размерность ленты.
 */
private const val TAPE_LENGTH = 30000

fun main(args: Array<String>) {
    val filename = Paths.get(args[0])
    Files.newBufferedReader(filename).use {
        val brainfuck = Brainfuck(CharArray(TAPE_LENGTH), it)
        brainfuck.run()
    }
}
