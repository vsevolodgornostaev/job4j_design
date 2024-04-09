package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleIsLogIn() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "LogIn"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("LogIn");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "LogIn"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleIsLogIn() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "LogIn"));
        store.add(new Role("1", "LogOut"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("LogIn");
    }

    @Test
    void whenReplaceThenRoleIsLogOut() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "LogIn"));
        store.replace("1", new Role("1", "LogOut"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("LogOut");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "LogIn"));
        store.replace("10", new Role("10", "LogOut"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("LogIn");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "LogIn"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleIsLogIn() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "LogIn"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("LogIn");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "LogIn"));
        boolean result = store.replace("1", new Role("1", "LogOut"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "LogIn"));
        boolean result = store.replace("10", new Role("10", "Maxim"));
        assertThat(result).isFalse();
    }

}