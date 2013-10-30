package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.base.Functions;
import com.github.agiledon.swift.base.Predicates;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public final class SwiftList {
    private SwiftList() {}

    public static <E> void foreach(List<E> iterable, Actions<? super E> action) {
        for (E element : iterable) {
            action.apply(element);
        }
    }

    public static <T, S> List<T> map(List<S> iterable, Functions<? super S, ? extends T> function) {
        List<T> result = newArrayList();
        for (S element : iterable) {
            result.add(function.apply(element));
        }
        return result;
    }

    public static <E> List<List<E>> partition(List<E> partitionFrom, Predicates<? super E> predicates) {
        List<List<E>> result = newArrayList();
        List<E> elementList = newArrayList();

        for (E element : partitionFrom) {
            if (predicates.apply(element)) {
                result.add(elementList);
                elementList = newArrayList();
            } else {
                elementList.add(element);
            }
        }
        if (elementList.size() > 0) {
            result.add(elementList);
        }
        return result;
    }
}
