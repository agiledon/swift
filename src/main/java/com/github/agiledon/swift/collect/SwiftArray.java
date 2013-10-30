package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;

public final class SwiftArray {
    private SwiftArray() {}

    public static <E> void foreach(E[] iterable, Actions<E> action) {
        for (E element : iterable) {
            action.apply(element);
        }
    }
}
