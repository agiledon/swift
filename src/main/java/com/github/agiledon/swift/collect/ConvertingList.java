package com.github.agiledon.swift.collect;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.take;

final class ConvertingList {
    private ConvertingList() {}

    static <E> E[] copyToArray(List<E> list, int start, int length) {
        return (E[]) take(list, start, length).toArray();
    }
}
