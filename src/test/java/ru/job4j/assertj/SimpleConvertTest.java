package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void conditionsOfArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).isNotNull()
                .allSatisfy(el -> {
                    assertThat(el.length()).isLessThan(15);
                    assertThat(el.length()).isGreaterThan(3);
                })
                .anySatisfy(el -> {
                    assertThat(el).isLowerCase();
                    assertThat(el.length()).isEqualTo(5);
                })
                .allMatch(el -> el.length() < 15)
                .anyMatch(el -> el.length() == 4)
                .noneMatch(el -> el.length() == 0);
    }

    @Test
    void conditionsOfArrayElement() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array[0]).isLowerCase()
                .isNotEmpty()
                .contains("first");
        assertThat(array[array.length-1].length()).isGreaterThan(3)
                .isLessThan(5)
                .isEqualTo(4);
    }

    @Test
    void conditionsOfGroupOfArrayElements() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).filteredOn(el -> el.length() < 5).contains("four", "five");
        assertThat(array).filteredOnAssertions(el -> assertThat(el).isLowerCase())
                .doesNotContainNull()
                .hasSizeBetween(4, 6);
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));

    }

    @Test
    void conditionsOfList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotNull()
                .allSatisfy(el -> {
                    assertThat(el.length()).isLessThan(15);
                    assertThat(el.length()).isGreaterThan(3);
                })
                .anySatisfy(el -> {
                    assertThat(el).isLowerCase();
                    assertThat(el.length()).isEqualTo(5);
                })
                .allMatch(el -> el.length() < 15)
                .anyMatch(el -> el.length() == 4)
                .noneMatch(el -> el.length() == 0);
    }

    @Test
    void conditionsOfListElement() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).element(1).isNotNull()
                .isEqualTo("second")
                .isNotSameAs(assertThat(list).element(0));
        assertThat(list).first().isNotSameAs(assertThat(list).last())
                .isEqualTo("first");
    }

    @Test
    void conditionsOfGroupOfListElement() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).filteredOn(el -> el.endsWith("e"))
                .hasSizeBetween(0, 2)
                .contains("three", "five")
                .doesNotContain("four", "eight");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "second", "three", "four", "five", "five");
        assertThat(set).hasSize(5)
                .contains("second")
                .doesNotHaveDuplicates()
                .doesNotContainNull();
    }

    @Test
    void conditionsOfSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "second", "three", "four", "five", "five");
        assertThat(set).isNotNull()
                .allSatisfy(el -> {
                    assertThat(el.length()).isLessThan(15);
                    assertThat(el.length()).isGreaterThan(3);
                })
                .anySatisfy(el -> {
                    assertThat(el).isLowerCase();
                    assertThat(el.length()).isEqualTo(5);
                })
                .allMatch(el -> el.length() < 15)
                .anyMatch(el -> el.length() == 4)
                .noneMatch(el -> el.length() == 0);
    }

    @Test
    void conditionsOfSetElement() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "second", "three", "four", "five", "five");
        assertThat(set).element(1).isNotNull()
                .isEqualTo("three")
                .isNotSameAs(assertThat(set).element(0));
        assertThat(set).first().isNotSameAs(assertThat(set).last())
                .isEqualTo("four");
    }

    @Test
    void conditionsOfGroupOfSetElement() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "second", "three", "four", "five", "five");
        assertThat(set).filteredOn(el -> el.endsWith("t"))
                .hasSizeBetween(0, 1)
                .contains("first")
                .doesNotContain("four", "eight");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .doesNotContainKey("six")
                .doesNotContainValue(6)
                .containsEntry("first", 0);
    }

    @Test
    void conditionsOfMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).isNotNull()
                .allSatisfy((key, value) -> {
                    assertThat(key).isLowerCase();
                    assertThat(key).isNotEmpty();
                    assertThat(value).isLessThan(6);
                })
                .anySatisfy((key, value) -> {
                    assertThat(key).contains("second");
                });
    }
}