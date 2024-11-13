package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean serverIsNotWorking = false;
        String timeServerStoped = null;
        List<String> log = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
               String[] splittedLine = line.split(" ");
               if ("400".equals(splittedLine[0]) || "500".equals(splittedLine[0])) {
                   if (!serverIsNotWorking) {
                       timeServerStoped = splittedLine[1] + ";";
                       serverIsNotWorking = true;
                   }
                } else {
                   if (serverIsNotWorking) {
                       timeServerStoped += splittedLine[1] + ";";
                       log.add(timeServerStoped);
                       serverIsNotWorking = false;
                   }
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            for (String el : log) {
                output.println(el);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
