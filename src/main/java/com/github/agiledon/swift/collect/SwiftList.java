package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Functions;
import com.github.agiledon.swift.base.Predicates;
import com.github.agiledon.swift.exception.ElementNotFoundException;

import java.util.ArrayList;
import java.util.List;

public final class SwiftList {
    private SwiftList() {
    }

    public static <T, S> List<T> map(List<S> list, Functions<? super S, ? extends T> function) {
        List<T> result = newArrayList();
        for (S element : list) {
            result.add(function.apply(element));
        }
        return result;
    }

    public static <E> List<E> take(List<E> list, int length) {
        List<E> result = newArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (i < length) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    public static <E> List<E> takeRight(List<E> list, int rightLength) {
        List<E> result = newArrayList();
        int value = list.size() - rightLength;
        int beginIndex = value >= 0 ? value : 0;
        for (; beginIndex < list.size(); beginIndex++) {
            result.add(list.get(beginIndex));
        }
        return result;
    }

    public static <E> List<E> takeWhile(List<E> list, Predicates<E> predicate) {
        List<E> result = newArrayList();
        for (E element : list) {
            if (predicate.apply(element)) {
                break;
            }
            result.add(element);
        }
        return result;
    }

    public static <E> List<E> drop(List<E> list, int length) {
        List<E> result = newArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (i >= length) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    public static <E> List<E> dropRight(List<E> list, int rightLength) {
        List<E> result = newArrayList();
        if (rightLength >= list.size()) {
            return result;
        }

        for (int i = 0; i < list.size() - rightLength; i++) {
            result.add(list.get(i));
        }

        return result;
    }

    public static <E> List<E> dropWhile(List<E> list, Predicates<E> predicate) {
        List<E> result = newArrayList();
        boolean startFlag = false;
        for (E element : list) {
            if (predicate.apply(element)) {
                startFlag = true;
            }
            if (startFlag) {
                result.add(element);
            }
        }
        return result;
    }

    public static <E> List<List<E>> split(List<E> partitionFrom, Predicates<? super E> predicates) {
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

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }

    public static <E> ArrayList<E> newArrayList(E... elements) {
        ArrayList<E> result = newArrayList();
        for (E element : elements) {
            result.add(element);
        }
        return result;
    }

    private static <E> void throwExceptionIfOneElement(List<E> list) {
        if (list.size() < 1) {
            throw new ElementNotFoundException();
        }
    }
}
