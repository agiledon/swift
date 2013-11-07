package com.github.agiledon.swift.collect.list;

import java.util.ArrayList;

final class ArrayListFactory {
    private ArrayListFactory() {}

    static <E> ArrayList<E> createArrayList() {
        return new ArrayList<E>();
    }

    static <E> ArrayList<E> createArrayList(E[] elements) {
        ArrayList<E> result = SwiftList.arrayList();
        for (E element : elements) {
            result.add(element);
        }
        return result;
    }
}
