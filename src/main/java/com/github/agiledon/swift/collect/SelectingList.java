package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Predicates;
import com.github.agiledon.swift.exception.ElementNotFoundException;

import java.util.List;

final class SelectingList {
    private SelectingList() {}

    static <E> List<E> take(List<E> list, int length) {
        List<E> result = SwiftList.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (i < length) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    static <E> List<E> takeRight(List<E> list, int rightLength) {
        List<E> result = SwiftList.newArrayList();
        int value = list.size() - rightLength;
        int beginIndex = value >= 0 ? value : 0;
        for (; beginIndex < list.size(); beginIndex++) {
            result.add(list.get(beginIndex));
        }
        return result;
    }

    static <E> List<E> takeWhile(List<E> list, Predicates<? super E> predicates) {
        List<E> result = SwiftList.newArrayList();
        for (E element : list) {
            if (predicates.apply(element)) {
                break;
            }
            result.add(element);
        }
        return result;
    }

    static <E> List<E> drop(List<E> list, int length) {
        List<E> result = SwiftList.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (i >= length) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    static <E> List<E> dropRight(List<E> list, int rightLength) {
        List<E> result = SwiftList.newArrayList();
        if (rightLength >= list.size()) {
            return result;
        }

        for (int i = 0; i < list.size() - rightLength; i++) {
            result.add(list.get(i));
        }

        return result;
    }

    static <E> List<E> dropWhile(List<E> list, Predicates<? super E> predicates) {
        List<E> result = SwiftList.newArrayList();
        boolean startFlag = false;
        for (E element : list) {
            if (predicates.apply(element)) {
                startFlag = true;
            }
            if (startFlag) {
                result.add(element);
            }
        }
        return result;
    }

    static <E> E head(List<E> list) {
        throwExceptionIfOneElement(list);
        return list.get(0);
    }

    static <E> E last(List<E> list) {
        throwExceptionIfOneElement(list);
        return list.get(list.size() - 1);
    }

    private static <E> void throwExceptionIfOneElement(List<E> list) {
        if (list.size() < 1) {
            throw new ElementNotFoundException();
        }
    }

    static <E> List<E> tail(List<E> list) {
        if (list.size() < 2) {
            return SwiftList.newArrayList();
        }
        List<E> result = SwiftList.newArrayList();
        for (int i = 1; i < list.size(); i++) {
            result.add(list.get(i));
        }
        return result;
    }

    static <E> List<E> init(List<E> list) {
        if (list.size() < 2) {
            return SwiftList.newArrayList();
        }
        List<E> result = SwiftList.newArrayList();
        for (int i = 0; i < list.size() - 1; i++) {
            result.add(list.get(i));
        }
        return result;
    }

    static <E> List<E> filter(List<E> filterFrom, Predicates<? super E> predicates) {
        List<E> result = SwiftList.newArrayList();
        for (E element : filterFrom) {
            if (predicates.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    static <E> List<E> filterNot(List<E> filterFrom, Predicates<? super E> predicates) {
        List<E> result = SwiftList.newArrayList();
        for (E element : filterFrom) {
            if (!predicates.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }
}
