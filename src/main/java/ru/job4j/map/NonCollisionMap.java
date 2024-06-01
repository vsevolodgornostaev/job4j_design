package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            result = true;
            modCount++;
            count++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode;
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> mapEntry : table) {
            if (mapEntry != null) {
                int index = indexFor(hash(Objects.hashCode(mapEntry.key)));
                newTable[index] = mapEntry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] != null) {
            if (Objects.hashCode(table[index].key) == Objects.hashCode(key)
                    && Objects.equals(table[index].key, key)) {

                result = table[index].value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] != null) {
            if (Objects.hashCode(table[index].key) == Objects.hashCode(key)
                    && Objects.equals(table[index].key, key)) {

                table[index] = null;
                result = true;
                modCount++;
                count--;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        return new Iterator<K>() {
            int element = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (element < table.length && table[element] == null) {
                    element++;
                }
                return element < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[element++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
