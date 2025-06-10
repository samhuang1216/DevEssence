package com.example.circular;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple circular singly linked list.
 */
public class CircularLinkedList<T> implements Iterable<T> {
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) { this.value = value; }
    }

    /** Adds an element to the end of the list. */
    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (tail == null) {
            tail = node;
            tail.next = tail;
        } else {
            node.next = tail.next;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = tail == null ? null : tail.next;
            private int visited = 0;
            @Override
            public boolean hasNext() {
                return visited < size;
            }
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T val = current.value;
                current = current.next;
                visited++;
                return val;
            }
        };
    }
}
