package com.example.circular;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {
    @Test
    void addAndIterate() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int sum = 0;
        for (int val : list) {
            sum += val;
        }
        assertEquals(6, sum);
        assertEquals(3, list.size());
    }
}
