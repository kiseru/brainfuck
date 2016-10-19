package com.brainfuck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * @author Alexandr Kiselev, Valery Petrov
 * 11-602
 * Brainfuck
 *
 */

public class Brainfuck {
// атрибуты
    private char[] tape; // входная лента
    private int i; // адрес текущей ячейки
    private int n = 30000; // размерность ленты
    private BufferedReader reader; // поток чтения из файла

// методы
    // конструктор по умолчанию (запрещён)
    private Brainfuck() {}

    // конструктор
    public Brainfuck(String fileName) throws Exception {
        tape = new char[n];
        i = n / 2;
        reader = new BufferedReader(new FileReader(fileName));
    }

    // движение вправо по входной ленте
    private void next() throws Exception {
        if (i < n) i++;
        else throw new Exception("Error. Out of bounds of the array");
    }

    // движение влево по входной ленте
    private void prev() throws Exception {
        if (i > 0) i--;
        else throw new Exception("Error. Out of bounds of the array");
    }

    // увеличить значение текущей ячейки
    private void add() {
        tape[i]++;
    }

    // уменьшить значение текущей ячейки
    private void sub() {
        tape[i]--;
    }

    // вывести на выходную ленту значение текущей ячейки
    private void print() {
        System.out.print(tape[i]);
    }

    // ввод значения ячейки извне
    private void set(char element) {
        tape[i] = element;
    }

    // реализация цикла
    private void cycle() throws Exception {
        // динамический массив, в котором лежит тело цикла
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

    // распознавание элемента с поседующими действиями
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

    // запуск интерпретатора
    public void run() throws Exception {
        while (reader.ready()) {
            make(read());
        }
    }

    // считывание значения ячейки
    private char read() throws Exception {
        return (char)reader.read();
    }
}
