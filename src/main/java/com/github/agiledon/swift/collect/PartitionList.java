package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Predicates;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.dropWhile;
import static com.github.agiledon.swift.collect.SwiftList.takeWhile;

final class PartitionList {
    private PartitionList() {
    }

    static <E> List<List<E>> split(List<E> splitFrom, Predicates<? super E> predicates) {
        List<List<E>> result = SwiftList.newArrayList();
        List<E> elementList = SwiftList.newArrayList();

        for (E element : splitFrom) {
            if (predicates.apply(element)) {
                result.add(elementList);
                elementList = SwiftList.newArrayList();
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
        List<List<E>> result = SwiftList.newArrayList();
        result.add(takeWhile(partitionFrom, predicates));
        result.add(dropWhile(partitionFrom, predicates));
        return result;
    }
}