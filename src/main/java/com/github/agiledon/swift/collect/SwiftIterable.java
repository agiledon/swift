package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;

public final class SwiftIterable {
    private SwiftIterable() {}

    public static <E> void foreach(Iterable<E> list, Actions<? super E> action) {
        for (E element : list) {
            action.apply(element);
        }
    }
}