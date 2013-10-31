package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Functions;
import com.github.agiledon.swift.base.Predicates;

import java.util.ArrayList;
import java.util.List;

public final class SwiftList {
    private SwiftList() {
    }

    public static <T, S> List<T> map(List<S> list, Functions<? super S, ? extends T> function) {
        return TransformingList.transform(list, function);
    }

    public static <E> List<E> take(List<E> list, int length) {
        return SelectingList.take(list, length);
    }

    public static <E> List<E> takeRight(List<E> list, int rightLength) {
        return SelectingList.takeRight(list, rightLength);
    }

    public static <E> List<E> takeWhile(List<E> list, Predicates<? super E> predicates) {
        return SelectingList.takeWhile(list, predicates);
    }

    public static <E> List<E> drop(List<E> list, int length) {
        return SelectingList.drop(list, length);
    }

    public static <E> List<E> dropRight(List<E> list, int rightLength) {
        return SelectingList.dropRight(list, rightLength);
    }

    public static <E> List<E> dropWhile(List<E> list, Predicates<? super E> predicates) {
        return SelectingList.dropWhile(list, predicates);
    }

    public static <E> E head(List<E> list) {
        return SelectingList.head(list);
    }

    public static <E> E last(List<E> list) {
        return SelectingList.last(list);
    }

    public static <E> List<E> tail(List<E> list) {
        return SelectingList.tail(list);
    }

    public static <E> List<E> init(List<E> list) {
        return SelectingList.init(list);
    }

    public static <E> List<E> filter(List<E> filterFrom, Predicates<? super E> predicates) {
        return SelectingList.filter(filterFrom, predicates);
    }

    public static <E> List<E> filterNot(List<E> filterFrom, Predicates<? super E> predicates) {
        return SelectingList.filterNot(filterFrom, predicates);
    }

    public static <E> List<List<E>> split(List<E> partitionFrom, Predicates<? super E> predicates) {
        return PartitionList.split(partitionFrom, predicates);
    }

    public static <E> List<List<E>> partition(List<E> partitionFrom, Predicates<? super E> predicates) {
        return PartitionList.partition(partitionFrom, predicates);
    }

    public static <E> ArrayList<E> newArrayList() {
        return ArrayListFactory.createArrayList();
    }

    public static <E> ArrayList<E> newArrayList(E... elements) {
        return ArrayListFactory.createArrayList(elements);
    }

}
