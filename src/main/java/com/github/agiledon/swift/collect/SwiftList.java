package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.base.Predicates;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public final class SwiftList {
    private SwiftList() {}
    public static <E> List<List<E>> partition(List<E> partitionFrom, Predicates<? super E> predicates) {
        List<List<E>> partitions = newArrayList();
        List<E> elementList = newArrayList();

        for (E element : partitionFrom) {
            if (predicates.apply(element)) {
                partitions.add(elementList);
                elementList = newArrayList();
            } else {
                elementList.add(element);
            }
        }
        if (elementList.size() > 0) {
            partitions.add(elementList);
        }
        return partitions;
    }

    public static <E> void foreach(List<E> iterable, Actions<? super E> action) {
        for (E element : iterable) {
            action.apply(element);
        }
    }
}
