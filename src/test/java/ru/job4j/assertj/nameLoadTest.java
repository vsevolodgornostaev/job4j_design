package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class nameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void namesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        String names = "";
        assertThatThrownBy(()->nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names);
    }

    @Test
    void nameContainsIncorrectSymbol() {
        NameLoad nameLoad = new NameLoad();
        String names = "key:value";
        assertThatThrownBy(()->nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names);
    }

    @Test
    void nameDoesNotContainAKey() {
        NameLoad nameLoad = new NameLoad();
        String names = "=value";
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names);
    }

    @Test
    void nameDoesNotContainAValue() {
        NameLoad nameLoad = new NameLoad();
        String names = "key=";
        assertThatThrownBy(()->nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names);
    }
}