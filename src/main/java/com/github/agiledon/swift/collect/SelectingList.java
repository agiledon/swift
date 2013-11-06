package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Predicates;
import com.github.agiledon.swift.exception.ElementNotFoundException;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.arrayList;

final class SelectingList {
    private SelectingList() {
    }

    static <E> List<E> take(List<E> list, int length) {
        List<E> result = arrayList();
        for (int i = 0; i < list.size(); i++) {
            if (i < length) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    public static <E> List<E> take(List<E> list, int start, int length) {
        List<E> result = arrayList();
        int realStart = start - 1;
        int end = realStart + length <= list.size() ? realStart + length : list.size();
        for (int i = realStart; i < end; i++) {
            result.add(list.get(i));
        }
        return result;
    }

    static <E> List<E> takeRight(List<E> list, int rightLength) {
        List<E> result = arrayList();
        int value = list.size() - rightLength;
        int beginIndex = value >= 0 ? value : 0;
        for (; beginIndex < list.size(); beginIndex++) {
            result.add(list.get(beginIndex));
        }
        return result;
    }

    static <E> List<E> takeWhile(List<E> list, Predicates<? super E> predicates) {
        List<E> result = arrayList();
        for (E element : list) {
            if (predicates.apply(element)) {
                break;
            }
            result.add(element);
        }
        return result;
    }

    static <E> List<E> drop(List<E> list, int length) {
        List<E> result = arrayList();
        for (int i = 0; i < list.size(); i++) {
            if (i >= length) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    static <E> List<E> dropRight(List<E> list, int rightLength) {
        List<E> result = arrayList();
        if (rightLength >= list.size()) {
            return result;
        }

        for (int i = 0; i < list.size() - rightLength; i++) {
            result.add(list.get(i));
        }

        return result;
    }

    static <E> List<E> dropWhile(List<E> list, Predicates<? super E> predicates) {
        List<E> result = arrayList();
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
            return arrayList();
        }
        List<E> result = arrayList();
        for (int i = 1; i < list.size(); i++) {
            result.add(list.get(i));
        }
        return result;
    }

    static <E> List<E> init(List<E> list) {
        if (list.size() < 2) {
            return arrayList();
        }
        List<E> result = arrayList();
        for (int i = 0; i < list.size() - 1; i++) {
            result.add(list.get(i));
        }
        return result;
    }

    static <E> List<E> filter(List<E> filterFrom, Predicates<? super E> predicates) {
        List<E> result = arrayList();
        for (E element : filterFrom) {
            if (predicates.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    static <E> List<E> filterNot(List<E> filterFrom, Predicates<? super E> predicates) {
        List<E> result = arrayList();
        for (E element : filterFrom) {
            if (!predicates.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    static <E> List<E> slice(List<E> list, int from, int until) {
        List<E> result = arrayList();
        if (from > until) {
            int temp = from;
            from = until;
            until = temp;
        }

        until = correctUntilValue(list, until);
        from = correctFromValue(from);

        for (int i = from; i < until; i++) {
            result.add(list.get(i));
        }
        return result;
    }

    private static int correctFromValue(int from) {
        return from < 0 ? 0 : from;
    }

    private static <E> int correctUntilValue(List<E> list, int until) {
        return until >= list.size() ? list.size() : until;
    }
}
