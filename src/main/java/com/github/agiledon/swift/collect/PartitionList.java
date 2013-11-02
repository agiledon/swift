package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Predicates;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.*;

final class PartitionList {
    private PartitionList() {
    }

    static <E> List<List<E>> split(List<E> splitFrom, Predicates<? super E> predicates) {
        List<List<E>> result = SwiftList.arrayList();
        List<E> elementList = SwiftList.arrayList();

        for (E element : splitFrom) {
            if (predicates.apply(element)) {
                result.add(elementList);
                elementList = SwiftList.arrayList();
            } else {
                elementList.add(element);
            }
        }
        if (elementList.size() > 0) {
            result.add(elementList);
        }
        return result;
    }

    static <E> List<List<E>> partition(List<E> partitionFrom, Predicates<? super E> predicates) {
        List<List<E>> result = SwiftList.arrayList();
        result.add(takeWhile(partitionFrom, predicates));
        result.add(dropWhile(partitionFrom, predicates));
        return result;
    }

    public static <E> List<List<E>> splitAt(List<E> splitFrom, int at) {
        List<List<E>> result = SwiftList.arrayList();
        result.add(take(splitFrom, at));
        result.add(drop(splitFrom, at));
        return result;
    }
}
