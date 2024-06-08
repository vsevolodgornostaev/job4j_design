package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {

    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> temp = findBy(parent);
        if (temp.isPresent() && !findBy(child).isPresent()) {
            temp.get().children.add(new Node<>(child));
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = element -> element.value.equals(value);
        return findByPredicate(predicate);
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }

    public boolean isBinary() {
        Predicate<Node<E>> predicate = element -> element.children.size() > 2;
        return findByPredicate(predicate).isEmpty();

    }
}
