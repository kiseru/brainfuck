package com.kiseru.brainfuck

import com.kiseru.brainfuck.exception.IllegalSymbolToProcess
import java.io.BufferedReader
import java.io.Reader
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Размерность ленты.
 */
private const val TAPE_LENGTH = 30000

/**
 * Лента.
 */
private val tape = CharArray(TAPE_LENGTH)

/**
 * Указатель на ячейку ленты, с которой идет взаимодействие.
 */
private var pointer = tape.size / 2

fun main(args: Array<String>) {
    val filename = Paths.get(args[0])
    Files.newBufferedReader(filename).use {
        runInterpreter(it)
    }
}

private fun runInterpreter(reader: BufferedReader) {
    while (reader.ready()) {
        make(reader, reader.read().toChar())
    }
}

/**
 * Запускает необходимую команду исходя из полученного символа.
 *
 * @throws com.kiseru.brainfuck.exception.IllegalSymbolToProcess
 *         если получает на вход символ, который невозможно обработать
 */
private fun make(reader: Reader, value: Char) {
    when (value) {
        '+' -> tape[pointer]++
        '-' -> tape[pointer]--
        '>' -> pointer++
        '<' -> pointer--
        '.' -> print(tape[pointer])
        ',' -> tape[pointer] = reader.read().toChar()
        '[' -> cycle(reader)
        else -> throw IllegalSymbolToProcess("Получен неверный символ для обработки")
    }
}

/**
 * Запускает цикл.
 */
private fun cycle(reader: Reader) {
    val list = ArrayList<Char>()
    var input: Char
    while (reader.read().toChar().also { input = it } != ']') {
        list.add(input)
    }
    while (tape[pointer].code != 0) {
        for (j in list.indices) {
            make(reader, list[j])
        }
    }
}
