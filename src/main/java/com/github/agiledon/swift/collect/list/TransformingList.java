package com.github.agiledon.swift.collect.list;

import com.github.agiledon.swift.base.Functions;

import java.util.List;

final class TransformingList {
    private TransformingList() {}

    static <T, S> List<T> transform(List<S> list, Functions<? super S, ? extends T> function) {
        List<T> result = SwiftList.arrayList();
        for (S element : list) {
            result.add(function.apply(element));
        }
        return result;
    }
}
