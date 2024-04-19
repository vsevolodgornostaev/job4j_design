package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> h = head;
        Node<E> newNode = new Node<>(value, null);
        if (h == null) {
            head = newNode;
        } else {
            while (h.next != null) {
                h = h.next;
            }
            h.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value, head);
        head = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = head;
        for (int count = 0; count < index; count++) {
            node = node.next;
        }
        return node.item;
    }

    public E deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        E firstItem = head.item;
        Node<E> nextNode = head.next;
        head.next = null;
        head.item = null;
        head = nextNode;
        size--;
        modCount++;
        return firstItem;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return new Iterator<E>() {
            Node<E> current = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> node = current;
                current = current.next;
                return node.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
