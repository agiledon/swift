package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Predicates;

import java.util.List;

final class QueryList {
    private QueryList() {}

    static <E> boolean exists(List<E> list, Predicates<? super E> predicates) {
        for (E element : list) {
            if (predicates.apply(element)) {
                return true;
            }
        }
        return false;
    }
}
