package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.PredicateAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 4, 4))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfElementsGreaterThanThree() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Predicate<Integer> predicate = element -> element > 3;
        ListUtils.removeIf(input, predicate);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfElementsLessThanThree() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Predicate<Integer> predicate = element -> element < 3;
        ListUtils.removeIf(input, predicate);
        assertThat(input).hasSize(3).containsSequence(3, 4, 5);
    }

    @Test
    void whenReplaceIfEvenElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Predicate<Integer> predicate = element -> element % 2 == 0;
        int value = 10;
        ListUtils.replaceIf(input, predicate, value);
        assertThat(input).hasSize(5).containsSequence(1, 10, 3, 10, 5);
    }

    @Test
    void whenRemoveAllIfFirstThreeElementsAreTheSame() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(2).containsSequence(4, 5);
    }

    @Test
    void whenRemoveAllIfTheSamePositionOfAllElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(0).containsSequence();
    }

    @Test
    void whenRemoveAllIfRandomPositionOfElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 5, 7));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(1).containsSequence(7);
    }
}
