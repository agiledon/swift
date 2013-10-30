package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Functions;
import com.github.agiledon.swift.base.Predicates;
import com.github.agiledon.swift.exception.ElementNotFoundException;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public final class SwiftList {
    private SwiftList() {}

    public static <T, S> List<T> map(List<S> list, Functions<? super S, ? extends T> function) {
        List<T> result = newArrayList();
        for (S element : list) {
            result.add(function.apply(element));
        }
        return result;
    }

    public static <E> List<E> take(List<E> list, int count) {
        List<E> result = newArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (i < count) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    public static <E> List<E> drop(List<E> list, int count) {
        List<E> result = newArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (i >= count) {
                result.add(list.get(i));
            }
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

    public static <E> E head(List<E> list) {
        throwExceptionIfOneElement(list);
        return list.get(0);
    }

    public static <E> E last(List<E> list) {
        throwExceptionIfOneElement(list);
        return list.get(list.size() - 1);
    }

    public static <E> List<E> tail(List<E> list) {
        if (list.size() < 2) {
            return newArrayList();
        }
        List<E> result = newArrayList();
        for (int i = 1; i < list.size(); i++) {
            result.add(list.get(i));
        }
        return result;
    }

    public static <E> List<E> init(List<E> list) {
        if (list.size() < 2) {
            return newArrayList();
        }
        List<E> result = newArrayList();
        for (int i = 0; i < list.size() - 1; i++) {
            result.add(list.get(i));
        }
        return result;
    }

    public static <E> List<E> filter(List<E> filterFrom, Predicates<E> predicate) {
        List<E> result = newArrayList();
        for (E element : filterFrom) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public static <E> List<E> filterNot(List<E> filterFrom, Predicates<E> predicate) {
        List<E> result = newArrayList();
        for (E element : filterFrom) {
            if (!predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    private static <E> void throwExceptionIfOneElement(List<E> list) {
        if (list.size() < 1) {
            throw new ElementNotFoundException();
        }
    }
}
