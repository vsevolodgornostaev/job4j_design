package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIterator(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == 0) {
            row++;
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (data[row].length == column) {
            column = 0;
            row++;
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row].length == 1 ? data[row++][column] : data[row][column++];
    }
}
