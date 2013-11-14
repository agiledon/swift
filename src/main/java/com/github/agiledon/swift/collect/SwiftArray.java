package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.base.BinaryPredicates;

public final class SwiftArray {
    private SwiftArray() {}

    public static <E> void each(E[] array, Actions<E> action) {
        for (E element : array) {
            action.apply(element);
        }
    }

    public static <E> void reverseEach(E[] array, Actions<E> action) {
        for (int i = array.length - 1; i >= 0; i--) {
            action.apply(array[i]);
        }
    }

    public static <E> boolean corresponds(E[] source, E[] target, BinaryPredicates<E> predicates) {
        if (source.length != target.length) {
            return false;
        }
        for (int index = 0; index < source.length; index++) {
            if (!predicates.apply(source[index], target[index])) {
                 return false;
            }
        }
        return true;
    }
}
