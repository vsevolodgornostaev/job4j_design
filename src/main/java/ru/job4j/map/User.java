package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    public static void main(String[] args) {
        User user1 = new User("Miguel", 2, new GregorianCalendar(1988, 2, 15));
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;

        User user2 = new User("Miguel", 2, new GregorianCalendar(1988, 2, 15));
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;

        Map<User, Object> map = new HashMap<>(16);
        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.printf("user1 - хэшкод: %s, хэш: %s, бакет: %s",
                hashCode1, hash1, bucket1);
        System.out.println();
        System.out.printf("user2 - хэшкод: %s, хэш: %s, бакет: %s",
                hashCode2, hash2, bucket2);
        System.out.println();
        System.out.println("Размер мапы: " + map.size());
    }
}
