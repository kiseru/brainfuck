package com.kiseru.brainfuck

fun main(args: Array<String>) {
    val brainfuck = Brainfuck(args[0])
    brainfuck.run()
}
