package ru.job4j.collection;


public class SimpleStack<T> {

    private SimpleLinked<T> linked = new SimpleLinkedList<T>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

}
