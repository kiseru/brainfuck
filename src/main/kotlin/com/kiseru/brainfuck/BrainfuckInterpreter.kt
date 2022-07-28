package com.kiseru.brainfuck

import com.kiseru.brainfuck.exception.IllegalSymbolToProcess
import java.io.BufferedReader
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
        make(reader, read(reader))
    }
}

/**
 * Читает символ из потока.
 *
 * @return символ
 */
private fun read(reader: BufferedReader) = reader.read().toChar()

/**
 * Запускает необходимую команду исходя из полученного символа.
 *
 * @throws com.kiseru.brainfuck.exception.IllegalSymbolToProcess
 *         если получает на вход символ, который невозможно обработать
 */
private fun make(reader: BufferedReader, value: Char) {
    when (value) {
        '+' -> add()
        '-' -> sub()
        '>' -> next()
        '<' -> prev()
        '.' -> print(tape[pointer])
        ',' -> set(read(reader))
        '[' -> cycle(reader)
        else -> throw IllegalSymbolToProcess("Получен неверный символ для обработки")
    }
}

/**
 * Увеличивает значение текущей ячейки единицу.
 */
private fun add() {
    tape[pointer]++
}

/**
 * Уменьшает значение текущей ячейки на единицу.
 */
private fun sub() {
    tape[pointer]--
}

/**
 * Перемещает указатель на ячейку вправо.
 */
private fun next() {
    pointer++
}

/**
 * Перемещает указатель на ячейку влево.
 */
private fun prev() {
    pointer--
}

/**
 * Задает ячейке новое значение.
 *
 * @param newValue новое значение
 */
private fun set(newValue: Char) {
    tape[pointer] = newValue
}

/**
 * Запускает цикл.
 */
private fun cycle(reader: BufferedReader) {
    val list = ArrayList<Char>()
    var input: Char
    while (read(reader).also { input = it } != ']') {
        list.add(input)
    }
    while (tape[pointer].code != 0) {
        for (j in list.indices) {
            make(reader, list[j])
        }
    }
}
