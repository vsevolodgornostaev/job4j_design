package ru.job4j.io;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    @SuppressWarnings({"checkstyle:EmptyBlock", "checkstyle:EmptyStatement"})
    public List<String> filter() {
        List<String> filteredList = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                String[] words = line.split(" ");
                if (words[words.length - 2].equals("404")) {
                    filteredList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filteredList;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}

