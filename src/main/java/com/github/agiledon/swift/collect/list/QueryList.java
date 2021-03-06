package com.github.agiledon.swift.collect.list;

import com.github.agiledon.swift.base.Predicates;
import com.github.agiledon.swift.exception.ElementNotFoundException;

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

    public static <E> E find(List<E> list, Predicates<? super E> predicates) {
        for (E element : list) {
            if (predicates.apply(element)) {
                return element;
            }
        }
        throw new ElementNotFoundException();
    }

    public static <E> boolean forall(List<E> list, Predicates<? super E> predicates) {
        boolean result = false;
        for (E element : list) {
            result = predicates.apply(element);
        }

        return result;
    }
}
