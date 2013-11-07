package com.github.agiledon.swift.collect.list;

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

    public static <E> List<E> take(List<E> list, int start, int length) {
        return SelectingList.take(list, start, length);
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

    public static <E> List<E> slice(List<E> list, int from, int until) {
        return SelectingList.slice(list, from, until);
    }

    public static <E> List<E> filter(List<E> filterFrom, Predicates<? super E> predicates) {
        return SelectingList.filter(filterFrom, predicates);
    }

    public static <E> List<E> filterNot(List<E> filterFrom, Predicates<? super E> predicates) {
        return SelectingList.filterNot(filterFrom, predicates);
    }

    public static <E> List<List<E>> split(List<E> splitFrom, Predicates<? super E> predicates) {
        return PartitionList.split(splitFrom, predicates);
    }

    public static <E> List<List<E>> splitAt(List<E> splitFrom, final int at) {
        return PartitionList.splitAt(splitFrom, at);
    }

    public static <E> List<List<E>> partition(List<E> partitionFrom, Predicates<? super E> predicates) {
        return PartitionList.partition(partitionFrom, predicates);
    }

    public static <E> E[] copyToArray(List<E> list, int start, int length) {
        return ConvertingList.copyToArray(list, start, length);
    }

    public static <E> boolean exists(List<E> list, Predicates<? super E> predicates) {
        return QueryList.exists(list, predicates);
    }

    public static <E> boolean forall(List<E> list, Predicates<? super E> predicates) {
        return QueryList.forall(list, predicates);
    }

    public static <E> E find(List<E> list, Predicates<? super E> predicates) {
        return QueryList.find(list, predicates);
    }

    public static <E extends Number> double sum(List<E> list) {
        return CountableList.sum(list);
    }

    public static <E extends Number> double average(List<E> list) {
        return sum(list) / list.size();
    }

    public static <E> String mkString(List<E> list, String before, String between, String after) {
        return OutputList.mkString(list, before, between, after);
    }

    public static String mkString(List<String> list) {
        return OutputList.mkString(list, "", "", "");
    }

    public static <E> ArrayList<E> arrayList() {
        return ArrayListFactory.createArrayList();
    }

    public static <E> ArrayList<E> arrayList(E... elements) {
        return ArrayListFactory.createArrayList(elements);
    }
}
