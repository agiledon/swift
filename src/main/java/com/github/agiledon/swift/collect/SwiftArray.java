package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.base.BinaryPredicates;

public final class SwiftArray {
    private SwiftArray() {}

    public static <E> void foreach(E[] iterable, Actions<E> action) {
        for (E element : iterable) {
            action.apply(element);
        }
    }

    public static <E> boolean corresponds(E[] first, E[] second, BinaryPredicates<E> predicates) {
        if (first.length != second.length) {
            return false;
        }
        for (int index = 0; index < first.length; index++) {
            if (!predicates.apply(first[index], second[index])) {
                 return false;
            }
        }
        return true;
    }
}
