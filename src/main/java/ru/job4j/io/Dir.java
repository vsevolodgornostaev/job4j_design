package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format(String.format("Это не директория: %s", file.getAbsoluteFile())));
        }
        System.out.println(String.format("Размер директории: %s", file.getTotalSpace()));
        String[] fileName = file.list();
        for (String el : fileName) {
            System.out.println("Имя файла: " + el + "\n"
                    + "Размер: " + el.length() + "\n");

        }
    }
}
