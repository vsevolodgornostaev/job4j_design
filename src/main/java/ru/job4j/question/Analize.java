package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> prevUsers = new HashMap<>();
        Map<Integer, String> currUsers = new HashMap<>();

        for (User el: previous) {
            prevUsers.put(el.getId(), el.getName());
        }

        for (User el: current) {
            currUsers.put(el.getId(), el.getName());
        }

       for (Map.Entry<Integer, String> prev: prevUsers.entrySet()) {
            if (currUsers.containsKey(prev.getKey())) {
                if (!currUsers.get(prev.getKey()).equals(prev.getValue())) {
                    info.setChanged(1);
                }
            } else {
                info.setDeleted(1);
            }
        }

        for (Map.Entry<Integer, String> curr: currUsers.entrySet()) {
            if (!prevUsers.containsKey(curr.getKey())) {
                info.setAdded(1);
            }
        }
        return info;
    }
}
