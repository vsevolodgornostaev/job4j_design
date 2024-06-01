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
        if (count == LOAD_FACTOR * capacity) {
            expand();
        }
        if (table[index(key)] == null) {
            table[index(key)] = new MapEntry<>(key, value);
            result = true;
            modCount++;
            count++;
        }
        return result;
    }

    private boolean checkKey(K key) {
        return Objects.hashCode(table[index(key)].key) == Objects.hashCode(key)
                    && Objects.equals(table[index(key)].key, key);
    }

    private int index(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> mapEntry : table) {
            if (mapEntry != null) {
                newTable[index(mapEntry.key)] = mapEntry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        if (table[index(key)] != null) {
            if (checkKey(key)) {
                result = table[index(key)].value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        if (table[index(key)] != null) {
            if (checkKey(key)) {
                table[index(key)] = null;
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
