package ru.job4j.tree;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void when12ElFindLastThen12() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(2, 5);
        tree.add(2, 6);
        tree.add(2, 7);
        tree.add(3, 8);
        tree.add(3, 9);
        tree.add(4, 10);
        tree.add(5, 11);
        assertThat(tree.findBy(11)).isPresent();
    }

    @Test
    void whenAddDuplicateIsFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.add(1, 2)).isFalse();
    }

    @Test
    void whenChildHasParentsValueIsFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.add(1, 2)).isTrue();
        assertThat(tree.add(2, 1)).isFalse();
        assertThat(tree.add(1, 6)).isTrue();
        assertThat(tree.add(6, 3)).isTrue();
    }
}