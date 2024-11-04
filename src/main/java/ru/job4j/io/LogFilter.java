package ru.job4j.io;

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
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                String[] words = line.split(" ");
                for (int i = words.length - 2; i < words.length - 1; i++) {
                    if (words[i].equals("404")) {
                        System.out.println(line);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}

