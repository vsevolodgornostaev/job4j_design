package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentsAndEmptyStrings() {
        String path = "./data/pair_with_comments_and_empty_strings.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver");
    }

    @Test
    void pairWithEqualitySymbolInValue() {
        String path = "./data/pair_with_equality_symbol_in_value.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect=");
    }

    @Test
    void pairWithEqualitySymbolAndOneInValue() {
        String path = "./data/pair_with_equality_symbol_and_one_in_value.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect=1");
    }

    @Test
    void pairWithoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        try {
            config.load();
        } catch (IllegalArgumentException ex) {
            assertEquals(null, ex.getMessage());
        }
    }

    @Test
    void pairWithoutValue() {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        try {
            config.load();
        } catch (IllegalArgumentException ex) {
            assertEquals(null, ex.getMessage());
        }
    }

    @Test
    void pairWithoutEqualitySymbol() {
        String path = "./data/pair_without_equality_symbol.properties";
        Config config = new Config(path);
        try {
            config.load();
        } catch (IllegalArgumentException ex) {
            assertEquals(null, ex.getMessage());
        }
    }

    @Test
    void stringWithEqualitySymbolOnly() {
        String path = "./data/string_with_equality_symbol_only.properties";
        Config config = new Config(path);
        try {
            config.load();
        } catch (IllegalArgumentException ex) {
            assertEquals(null, ex.getMessage());
        }
    }
}