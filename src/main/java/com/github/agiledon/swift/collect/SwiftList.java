package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Predicates;
import java.util.List;
import static com.google.common.collect.Lists.newArrayList;

public final class SwiftList {
    private SwiftList() {}
    public static <E> List<List<E>> partition(List<E> target, Predicates<E> predicate) {
        List<List<E>> partitions = newArrayList();
        List<E> elementList = newArrayList();
        for (E element : target) {
            if (predicate.apply(element)) {
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
}
