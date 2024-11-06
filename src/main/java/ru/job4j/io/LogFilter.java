package ru.job4j.io;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> filteredList = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                String[] words = line.split(" ");
                if ("404".equals(words[words.length - 2])) {
                    filteredList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filteredList;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            for (var el : data) {
                output.println(el);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}

