package com.kiseru.brainfuck;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Brainfuck {

    /**
     * Входная лента.
     */
    private char[] tape;

    /**
     * Указатель на текущую ячейку.
     */
    private int i;

    /**
     * Поток чтения из файла.
     */
    private BufferedReader reader;

    public Brainfuck(char[] tape, BufferedReader reader) {
        this.tape = tape;
        this.reader = reader;
        i = tape.length / 2;
    }

    /**
     * Передвигает указатель ячейки вправо по входной ленте.
     *
     * @throws java.lang.Exception если вышли за границы ленты
     */
    private void next() throws Exception {
        if (i < tape.length) {
            i++;
        } else {
            throw new Exception("Error. Out of bounds of the array");
        }
    }

    /**
     * Передвигает указатель ячейки влево по входной ленте.
     *
     * @throws java.lang.Exception если вышли за границы ленты
     */
    private void prev() throws Exception {
        if (i > 0) {
            i--;
        } else {
            throw new Exception("Error. Out of bounds of the array");
        }
    }

    /**
     * Увеличивает значение текущей ячейки.
     */
    private void add() {
        tape[i]++;
    }

    /**
     * Уменьшает значение текущей ячейки.
     */
    private void sub() {
        tape[i]--;
    }

    /**
     * Выводит на экран значение текущей ячейки
     */
    private void print() {
        System.out.print(tape[i]);
    }

    /**
     * Задает текущей ячейке новое значение.
     *
     * @param newValue новое значение
     */
    private void set(char newValue) {
        tape[i] = newValue;
    }

    /**
     * Реализация цикла.
     */
    private void cycle() throws Exception {
        ArrayList<Character> list = new ArrayList<>();
        char input;
        while ((input = read()) != ']') {
            list.add(input);
        }
        while (tape[i] != 0) {
            for (int j = 0; j < list.size(); j++) {
                make(list.get(j));
            }
        }
    }

    /**
     * Распознавание элемента с последующими действиями.
     *
     * @throws java.lang.Exception если считает неверные символы кода
     */
    private void make(char element) throws Exception {
        if (element == '+') {
            add();
        } else if (element == '-') {
            sub();
        } else if (element == '>') {
            next();
        } else if (element == '<') {
            prev();
        } else if (element == '.') {
            print();
        } else if (element == ',') {
            set(read());
        } else if (element == '[') {
            cycle();
        } else if (element != '\n' && element != ' ' && element != '\r') {
            throw new Exception("");
        }
    }

    /**
     * Запускает интерпретатор.
     *
     * @throws java.io.IOException если произойдет ошибка связанная с I/O
     * @throws java.lang.Exception если считает неверные символы кода
     */
    public void run() throws Exception {
        while (reader.ready()) {
            make(read());
        }
    }

    /**
     * Считывает значения ячейки
     *
     * @throws java.io.IOException если произойдет ошибка связанная с I/O
     */
    private char read() throws IOException {
        return (char) reader.read();
    }
}
